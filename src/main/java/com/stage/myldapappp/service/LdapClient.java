package com.stage.myldapappp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service

public class LdapClient {

    @Autowired
    private Environment env;

    @Autowired
    private ContextSource contextSource;

    @Autowired
    private LdapTemplate ldapTemplate;

    Logger logger = LoggerFactory.getLogger(LdapClient.class);

    public void authenticate(final String username, final String password) {
        contextSource.getContext("cn=" + username + "," + env.getRequiredProperty("ldap.partitionSuffix"), password);
    }


    public List<LdapEntry> searchByCommonName(final String commonName) {
        System.out.println(ldapTemplate.getContextSource());
        System.out.println(ldapTemplate);

        logger.error("FUCKAMAURY");
        logger.error(ldapTemplate.getContextSource().toString());
        logger.error(ldapTemplate.toString());
        logger.error("=====================================");

        logger.debug("FUCKAMAURY");
        logger.debug(ldapTemplate.getContextSource().toString());
        logger.debug(ldapTemplate.toString());
        logger.debug("=====================================");

        return ldapTemplate.search(
                "",
                "(cn=" + commonName + ")",
                (AttributesMapper<LdapEntry>) attrs -> {
                    LdapEntry entry = new LdapEntry();
                    entry.setCn((String) attrs.get("cn").get());
                    entry.setSn((String) attrs.get("sn").get());
                    return entry;
                }
        );
    }



    public void create(final String username, final String password) {
        Name dn = LdapNameBuilder.newInstance()
                .add("ou", "people")
                .add("cn", username)
                .build();
        DirContextAdapter context = new DirContextAdapter(dn);

        context.setAttributeValues("objectclass", new String[]{"top", "person", "organizationalPerson", "inetOrgPerson"});
        context.setAttributeValue("cn", username);
        context.setAttributeValue("sn", username);
        context.setAttributeValue("userPassword", digestSHA(password));

        ldapTemplate.bind(context);
    }

    public void modify(final String username, final String password) {
        Name dn = LdapNameBuilder.newInstance()
                .add("ou", "people")
                .add("cn", username)
                .build();
        DirContextOperations context = ldapTemplate.lookupContext(dn);

        context.setAttributeValues("objectclass", new String[]{"top", "person", "organizationalPerson", "inetOrgPerson"});
        context.setAttributeValue("cn", username);
        context.setAttributeValue("sn", username);
        context.setAttributeValue("userPassword", digestSHA(password));

        ldapTemplate.modifyAttributes(context);
    }

    private String digestSHA(final String password) {
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(password.getBytes());
            base64 = Base64.getEncoder().encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{SHA}" + base64;
    }
}
