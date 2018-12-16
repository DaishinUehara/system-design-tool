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
public class ProgramDesign {
//	@GeneratedValue
	@Id
	private String programId; /** プログラムID */
	private String programName; /** プログラム名 */

	// 楽観的排他制御に用いるバージョンフィールドの指定
	@Version
	private Integer version;

	@OneToMany(mappedBy="programDesign")
	List<FormDesign> formDesigns;

}
