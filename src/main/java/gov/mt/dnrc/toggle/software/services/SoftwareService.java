package gov.mt.dnrc.toggle.software.services;

import com.google.common.collect.Lists;
import gov.mt.dnrc.toggle.software.models.Software;
import gov.mt.dnrc.toggle.software.repository.SoftwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareService {

    private SoftwareRepository softwareRepository;

    /**
     *
     * @param softwareRepository
     */
    @Autowired
    public SoftwareService(SoftwareRepository softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    public Software getSoftware(Long id) {
        return softwareRepository.findOne(id);
    }

    public List<Software> getAllSoftware() {
        return Lists.newArrayList(softwareRepository.findAll());
    }

    public Software saveSoftware(Software software) {
        return softwareRepository.save(software);
    }

}
