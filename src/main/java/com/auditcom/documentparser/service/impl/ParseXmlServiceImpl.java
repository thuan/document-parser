package com.auditcom.documentparser.service.impl;

import com.auditcom.documentparser.service.ParseXmlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParseXmlServiceImpl implements ParseXmlService {

    private final Logger log = LoggerFactory.getLogger(ParseXmlServiceImpl.class);
}
