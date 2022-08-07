package city.ac.licensing.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "license_type", schema = "public")
public class LicenseTypeEntity implements Serializable {
    private static final long serialVersionUID = -4820756067888551977L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "software_id")
    private SoftwareEntity software;

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

    public SoftwareEntity getSoftware() {
        return software;
    }

    public void setSoftware(SoftwareEntity software) {
        this.software = software;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LicenseTypeEntity)) return false;
        LicenseTypeEntity that = (LicenseTypeEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LicenseStatusEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
