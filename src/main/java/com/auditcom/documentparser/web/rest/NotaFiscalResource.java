package com.auditcom.documentparser.web.rest;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import com.auditcom.documentparser.service.AlimentoAcordanteEntradaService;
import com.auditcom.documentparser.service.dto.AlimentoAcordanteEntradaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;

/**
 * NotaFiscalResource controller
 */
@RestController
@RequestMapping("/api/nota-fiscal")
public class NotaFiscalResource {

    private final Logger log = LoggerFactory.getLogger(NotaFiscalResource.class);

    private static final String ENTITY_NAME = "notaFiscalAlimentoEntrada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlimentoAcordanteEntradaService alimentoAcordanteEntradaService;

    public NotaFiscalResource(AlimentoAcordanteEntradaService alimentoAcordanteEntradaService) {
        this.alimentoAcordanteEntradaService = alimentoAcordanteEntradaService;
    }

    /**
     * for (TNFe.InfNFe.Det tnFe : tnFe.getInfNFe().getDet()) {
     * TNFe.InfNFe.Det.Imposto imposto = tnFe.getImposto();
     * }
     * entradaDTO.setPercentualICMS();
     * entradaDTO.setCfop();
     * entradaDTO.setCst(tnFe.getInfNFe());
     * entradaDTO.setNormaExecucao();
     * <p>
     * pegar o xml > converter no DTO > salvar no banco e depois no storage
     * POST enviarNotas
     */
    @PostMapping(value = "/enviar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AlimentoAcordanteEntradaDTO> enviarNota(@RequestPart("file") MultipartFile file)
        throws URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, IOException {
        AlimentoAcordanteEntradaDTO entradaDTO = new AlimentoAcordanteEntradaDTO();
        try {
            /*InputStream initialStream = file.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);
            file.transferTo(new File("src/main/resources/targetFile.tmp"));*/

            String accessKey = "minioadmin";
            String secretKey = "minioadmin";

            MinioClient minioClient = MinioClient.builder().endpoint("http://127.0.0.1:9000").credentials(accessKey, secretKey).build();

            Path tempFile = Files.createTempFile("teste", ".xml");
            try (InputStream in = file.getInputStream()) {
                Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            String xmlEntrada = XmlNfeUtil.leXml(tempFile.toString());
            TNFe tnFe = XmlNfeUtil.xmlToObject(xmlEntrada, TNFe.class);
            entradaDTO.setIdNFe(tnFe.getInfNFe().getId());
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
            entradaDTO = alimentoAcordanteEntradaService.save(entradaDTO);

            UploadObjectArgs.Builder builder = UploadObjectArgs
                .builder()
                .bucket("notas-xml")
                .object(entradaDTO.getId() + ".xml")
                .filename(tempFile.toString());

            minioClient.uploadObject(builder.build());

            ObjectMapper mapper = new ObjectMapper();
            File fileJson = new File("entrada.json");
            mapper.writeValue(fileJson, entradaDTO);

            UploadObjectArgs.Builder builder2 = UploadObjectArgs
                .builder()
                .bucket("notas-json")
                .object(entradaDTO.getId() + ".json")
                .filename(fileJson.toString());

            minioClient.uploadObject(builder2.build());

            Files.delete(tempFile);
            Files.delete(fileJson.toPath());
        } catch (JAXBException | MinioException e) {
            e.printStackTrace();
        }

        return ResponseEntity
            .created(new URI("/api/enviar/" + entradaDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, entradaDTO.getId().toString()))
            .body(entradaDTO);
    }
}
