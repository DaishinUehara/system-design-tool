package uehara.daishin.sdtool.db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class JobDesign {
//	@GeneratedValue
	@Id
	private String jobId; /** 業務id */
	private String jobName; /** プログラム名 */
	private String callPath; /** パス */
	private String initGamenId; /** 初期表示画面id */

	// 楽観的排他制御に用いるバージョンフィールドの指定
	@Version
	private Integer version;

	@OneToMany(mappedBy="jobDesign")
	List<FormDesign> formDesigns;

}
