package in.softsolutionzone.persistance;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="master_bank_details")
public class BankDetailsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String bank;
	private int aeps_enabled;
	private int imps_enabled;
	private String bank_short_name;
	private String ifsc;
	private String bank_iin;
	private int id_down;
}
