package poly.persistence.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import poly.persistence.IDummyMapper;

@Component("DummyMapper")
public class DummyMapper implements IDummyMapper {

    private final Logger log = Logger.getLogger(this.getClass());

}
