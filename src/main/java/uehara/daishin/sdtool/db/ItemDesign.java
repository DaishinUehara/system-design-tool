package uehara.daishin.sdtool.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ItemDesign {
//	@GeneratedValue
	@Id
	private String itemId; /** 項目id  */
	private String itemName; /** 項目名  */
	private String formId; /** 画面ID */
	private String formName; /** 画面名 */
	private String controlType; /** コントロール種別 */
//	private String programId; /** プログラムID */
//	private String programName; /** プログラム名 */
//	private String eventUrlName;
	private String propertyType; /** 属性 */
	private Integer maxLength; /** 桁数 */
	private String mappingName; /** 変数名 */


	// 楽観的排他制御に用いるバージョンフィールドの指定
	@Version
	private Integer version;

	@ManyToOne
	FormDesign formDesign;

	@OneToMany
	DetailDesign detailDesign;

}
