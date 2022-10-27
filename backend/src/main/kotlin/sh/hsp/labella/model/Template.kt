package sh.hsp.labella.model

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "templates")
class Template {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "template")
    var template: String = ""

    @Column(name = "updated")
    var updated: Date? = null

    @PrePersist
    @PreUpdate
    fun generateUpdated(){
        updated = Date()
    }
}