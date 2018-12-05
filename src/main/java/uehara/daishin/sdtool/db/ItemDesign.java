package uehara.daishin.sdtool.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ItemDesign {
	@Id
	@GeneratedValue
	private Integer id;

	private String itemId;
	private String itemName;
	private String programId;
	private String programName;
	private Integer maxLength;
	private String displayId;
	private String displayName;
	private String controlId;
	private String controlName;
	private boolean focusoutEvent;
	private String eventUrlName;
	private String propertyType;

	// 楽観的排他制御に用いるバージョンフィールドの指定
	@Version
	private Integer version;

}
