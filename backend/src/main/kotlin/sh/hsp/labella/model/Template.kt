package sh.hsp.labella.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "templates")
class Template {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var id: Long? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "template")
    var template: String = ""

    @Column(name = "created")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var created: Date? = null

    @Column(name = "updated")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var updated: Date? = null

    @PrePersist
    fun onCreate() {
        updated = Date()
        created = Date()
    }
    @PreUpdate
    fun onUpdate() {
        updated = Date()
    }
}