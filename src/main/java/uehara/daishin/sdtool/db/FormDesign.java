package uehara.daishin.sdtool.db;

import java.util.List;

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
public class FormDesign {
//	@GeneratedValue
	@Id
	private String formId; /** 画面ID */
	private String formName; /** 画面名 */
	private String fileName; /** ファイル名 */

	// 楽観的排他制御に用いるバージョンフィールドの指定
	@Version
	private Integer version;

	@OneToMany(mappedBy="formDesign")
	List<ItemDesign> itemDesigns;

	@ManyToOne
	JobDesign jobDesign;

}
