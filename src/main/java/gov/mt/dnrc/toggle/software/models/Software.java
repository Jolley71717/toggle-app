package gov.mt.dnrc.toggle.software.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Software Entity used to store information
 * on software in a very simple fashion.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
public class Software implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @NotBlank
    @Column(nullable = false)
    private String name;

    @Size(max = 50)
    @Column
    private String version;

    @Size(max = 50)
    @Column
    private String vendor;

    public Software() {

    }

    public Software(String name) {
        this.name = name;
    }

    public Software(String name, String version, String vendor) {
        this.name = name;
        this.version = version;
        this.vendor = vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Software)) return false;

        Software software = (Software) o;

        if (!id.equals(software.id)) return false;
        if (!getName().equals(software.getName())) return false;
        if (getVersion() != null ? !getVersion().equals(software.getVersion()) : software.getVersion() != null)
            return false;
        return getVendor() != null ? getVendor().equals(software.getVendor()) : software.getVendor() == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getVendor() != null ? getVendor().hashCode() : 0);
        return result;
    }
}
