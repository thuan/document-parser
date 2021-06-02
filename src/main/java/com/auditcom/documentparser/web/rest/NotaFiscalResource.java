package com.auditcom.documentparser.web.rest;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.auditcom.documentparser.service.NotaFiscalEntradaService;
import com.auditcom.documentparser.service.ProdutoEntradaService;
import com.auditcom.documentparser.service.dto.NotaFiscalEntradaDTO;
import com.auditcom.documentparser.service.dto.ProdutoEntradaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * NotaFiscalResource controller
 */
@RestController
@RequestMapping("/api/nota-fiscal")
public class NotaFiscalResource {

    private final Logger log = LoggerFactory.getLogger(NotaFiscalResource.class);

    @Autowired
    private NotaFiscalEntradaService notaFiscalEntradaService;

    @Autowired
    private ProdutoEntradaService produtoEntradaService;

    @PostMapping(value = "/parsear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<NotaFiscalEntradaDTO> parsear(@RequestParam(value = "files") MultipartFile[] files)
        throws InvalidKeyException, NoSuchAlgorithmException, IOException {
        List<NotaFiscalEntradaDTO> lstNotas = new ArrayList<>();
        try {
            String accessKey = "minioadmin";
            String secretKey = "minioadmin";

            MinioClient minioClient = MinioClient.builder().endpoint("http://127.0.0.1:9000").credentials(accessKey, secretKey).build();

            for (MultipartFile file : files) {
                NotaFiscalEntradaDTO entradaDTO = new NotaFiscalEntradaDTO();
                ProdutoEntradaDTO produtoEntradaDTO = new ProdutoEntradaDTO();

                Path tempFile = Files.createTempFile("teste", ".xml");
                try (InputStream in = file.getInputStream()) {
                    Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
                }

                String xmlEntrada = XmlNfeUtil.leXml(tempFile.toString());
                TNFe tnFe = XmlNfeUtil.xmlToObject(xmlEntrada, TNFe.class);
                entradaDTO.setIdNfe(tnFe.getInfNFe().getId().substring(3));
                entradaDTO.setCrt(Integer.valueOf(tnFe.getInfNFe().getEmit().getCRT()));
                entradaDTO.setUfEmitente(tnFe.getInfNFe().getEmit().getEnderEmit().getUF().value());
                entradaDTO.setUfDestinatario(tnFe.getInfNFe().getDest().getEnderDest().getUF().value());
                entradaDTO.setValorItem(Double.valueOf(tnFe.getInfNFe().getTotal().getICMSTot().getVProd()));
                entradaDTO.setValorIPI(Double.valueOf(tnFe.getInfNFe().getTotal().getICMSTot().getVIPI()));
                entradaDTO.setValorFrete(Double.valueOf(tnFe.getInfNFe().getTotal().getICMSTot().getVFrete()));
                entradaDTO.setValorSeguro(Double.valueOf(tnFe.getInfNFe().getTotal().getICMSTot().getVSeg()));
                entradaDTO.setValorOutros(Double.valueOf(tnFe.getInfNFe().getTotal().getICMSTot().getVOutro()));
                entradaDTO.setCnpjEmitente(tnFe.getInfNFe().getEmit().getCNPJ());
                entradaDTO.setCnpjDestinatario(tnFe.getInfNFe().getDest().getCNPJ());
                entradaDTO.setCategoria("Categoria Teste");
                entradaDTO.setNormaExecucao("Norma Teste");
                for (TNFe.InfNFe.Det det : tnFe.getInfNFe().getDet()) {
                    produtoEntradaDTO.setCfop(Integer.valueOf(det.getProd().getCFOP()));
                    for (JAXBElement element : det.getImposto().getContent()) {
                        if (element.getValue().getClass().equals(TNFe.InfNFe.Det.Imposto.ICMS.class)) {
                            TNFe.InfNFe.Det.Imposto.ICMS icms = (TNFe.InfNFe.Det.Imposto.ICMS) element.getValue();
                            produtoEntradaDTO.setPercentualICMS(Double.valueOf(icms.getICMS00().getPICMS()));
                            produtoEntradaDTO.setCst(icms.getICMS00().getCST());
                        }
                    }
                }
                produtoEntradaDTO = produtoEntradaService.save(produtoEntradaDTO);

                entradaDTO.setProdutoEntrada(produtoEntradaDTO);

                NotaFiscalEntradaDTO saveDto = notaFiscalEntradaService.save(entradaDTO);

                saveDto.getProdutoEntrada().setPercentualICMS(produtoEntradaDTO.getPercentualICMS());

                saveDto.getProdutoEntrada().setCst(produtoEntradaDTO.getCst());

                saveDto.getProdutoEntrada().setCfop(produtoEntradaDTO.getCfop());

                entradaDTO = saveDto;

                UploadObjectArgs.Builder builderXml = UploadObjectArgs
                    .builder()
                    .bucket("notas-xml")
                    .object(entradaDTO.getId() + ".xml")
                    .filename(tempFile.toString());

                minioClient.uploadObject(builderXml.build());

                ObjectMapper mapper = new ObjectMapper();
                File fileJson = new File("entrada.json");
                mapper.writeValue(fileJson, entradaDTO);

                UploadObjectArgs.Builder builderJson = UploadObjectArgs
                    .builder()
                    .bucket("notas-json")
                    .object(entradaDTO.getId() + ".json")
                    .filename(fileJson.toString());

                minioClient.uploadObject(builderJson.build());

                Files.delete(tempFile);
                Files.delete(fileJson.toPath());

                log.debug("REST request to send XML Parser: {}", entradaDTO, produtoEntradaDTO);

                lstNotas.add(entradaDTO);
            }
        } catch (JAXBException | MinioException e) {
            e.printStackTrace();
        }

        return lstNotas;
    }
}
