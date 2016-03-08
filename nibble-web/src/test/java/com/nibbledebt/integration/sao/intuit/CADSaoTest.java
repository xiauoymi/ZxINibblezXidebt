package com.nibbledebt.integration.sao.intuit;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.integration.sao.IIntegrationSao;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author a.salachyonok
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class CADSaoTest {

    @Autowired
    @Qualifier("intuitCadSao")
    private IIntegrationSao integrationSao;

    @Test
    public void getInstitutionsTest() throws ServiceException{
        List<Institution> institutions = integrationSao.getInstitutions();
        Assert.assertNotNull(institutions);
        Assert.assertTrue(institutions.size() > 0);
    }
}
