package mx.gob.queretaro.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="actor")
@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a ORDER BY a.actorId DESC")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Actor implements Serializable {

	private static final long serialVersionUID = -2463243506444917966L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="actor_id", unique=true, nullable=false)
	private Short actorId;

	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

}
