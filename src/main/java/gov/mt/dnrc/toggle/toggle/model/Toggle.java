package gov.mt.dnrc.toggle.toggle.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity object for toggles. The objects goal is to store information on a
 * toggleable feature. In the future I may separate this to feature that implements
 * a toggleable class.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
public class Toggle implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String module;
    @Column(nullable = false)
    private Boolean enabled;
    @Column(nullable = false)
    private String version;
    @Column(nullable = false)
    private String type;
    @OneToMany
    private List<Toggle> dependencies;

    public Toggle() {}

    public Toggle(Long id, String module, Boolean enabled) {
        this.id = id;
        this.module = module;
        this.enabled = enabled;
    }

    public Toggle(String module, Boolean enabled, List<Toggle> dependencies) {
        this.module = module;
        this.enabled = enabled;
        this.dependencies = dependencies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Toggle> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Toggle> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toggle)) return false;

        Toggle toggle = (Toggle) o;

        if (!getId().equals(toggle.getId())) return false;
        if (!getModule().equals(toggle.getModule())) return false;
        if (!getEnabled().equals(toggle.getEnabled())) return false;
        if (!getVersion().equals(toggle.getVersion())) return false;
        return getType().equals(toggle.getType());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getModule().hashCode();
        result = 31 * result + getEnabled().hashCode();
        result = 31 * result + getVersion().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }
}
