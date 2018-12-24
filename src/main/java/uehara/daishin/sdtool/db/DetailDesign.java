package uehara.daishin.sdtool.db;

// import lombok.Getter;
// import lombok.Setter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;


public class DetailDesign {

	@Entity
	@Setter
	@Getter
	public class JobDesign {
//		@GeneratedValue
		@Id
		private String detailId; /** 明細id */
		private String detailItemId; /** 明細項目id */
		private String detailItemName; /** 明細項目名 */
		private String detailControlType; /** 明細項目コントロール種別 */
		private String detailPropertyType; /** 明細項目属性 */
		private String detailMaxLength; /** 明細項目桁数 */
		private String detailMappingName; /** 明細項目変数名 */

		// 楽観的排他制御に用いるバージョンフィールドの指定
		@Version
		private Integer version;

		@ManyToOne
		List<ItemDesign> itemDesigns;

	}

}

