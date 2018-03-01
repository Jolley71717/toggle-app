package gov.mt.dnrc.toggle.software.services;

import com.google.common.collect.Lists;
import gov.mt.dnrc.toggle.core.service.AbstractGenericService;
import gov.mt.dnrc.toggle.core.util.QRUtility;
import gov.mt.dnrc.toggle.software.models.Software;
import gov.mt.dnrc.toggle.software.repository.SoftwareRepository;
import gov.mt.dnrc.toggle.software.services.spi.ISoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class that is responsible for the business logic managing software.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class SoftwareService extends AbstractGenericService<Software, Long> implements ISoftwareService {

    private SoftwareRepository softwareRepository;

    /**
     * Generic Service Constructor for the Software module.
     * @param softwareRepository CRUD Repository implementation.
     */
    @Autowired
    public SoftwareService(SoftwareRepository softwareRepository) {
        super(softwareRepository);

        this.softwareRepository = softwareRepository;
    }

    /**
     * Retrieves the software record from the database
     *
     * @param id The id or primary key of a database table.
     * @return returns the software from the database.
     */
    @Override
    public Software retrieve(Long id) {
        Software software = super.retrieve(id);

        // Generate the QR Code from the string data.
        software.setQrCode(QRUtility.retrieveQRCodeImage(software));

        return software;
    }

    /**
     * Retrieves all of the software from the database.
     *
     * @return List of Software.
     */
    @Override
    public List<Software> retrieveAll() {

        List<Software> softwareArrayList = new ArrayList<>();

        // For each software, set the QR code and add it to the returned array list.
        softwareRepository.findAll().forEach(software -> {
            software.setQrCode(QRUtility.retrieveQRCodeImage(software));
            softwareArrayList.add(software);
        });

        // Generates the software.
        return Lists.newArrayList(softwareArrayList);
    }

}
