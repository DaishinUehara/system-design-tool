package uehara.daishin.sdtool;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Persistence;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import lombok.val;
import uehara.daishin.sdtool.apireader.ApiBookReader;
import uehara.daishin.sdtool.db.DetailDesign;
import uehara.daishin.sdtool.db.FormDesign;
import uehara.daishin.sdtool.db.ItemDesign;
import uehara.daishin.sdtool.db.WorkProgramDesign;
import uehara.daishin.sdtool.design.DesignData;
import uehara.daishin.sdtool.design.DesignDataBook;
import uehara.daishin.sdtool.design.DesignDataBooks;
import uehara.daishin.sdtool.design.DesignDataSheet;



/**
 * Hello world!
 *
 */
public class DesignDocumentReader
{
	public static void main( String[] args )
	{
		if (2 != args.length){
			System.err.println("ApiMaker ReadDirectory OutputDirectory");
			System.exit(1);
		}

		System.out.println( "[INFO]処理開始" );
		String arg0=args[0].replaceFirst("\\\\$", "");
		arg0=arg0.replaceFirst("\\/$", "");
		Path doc_dir = Paths.get(arg0);

		String arg1=args[1].replaceFirst("\\\\$", "");
		arg1=arg1.replaceFirst("\\/$", "");
		Path src_dir = Paths.get(arg1);
		if(!Files.exists(src_dir)){
			try {
				Files.createDirectories(src_dir);
			} catch (IOException e) {
				System.err.println( "[ERROR]ディレクトリ\""+src_dir+"\"の作成に失敗しました。" );
				e.printStackTrace();
				System.exit(1);
			}
		}

//		DesignDataBooks designDataBooks=new DesignDataBooks();
//		designDataBooks.setName("テスト設計書");
//		designDataBooks.setDesignDataBookList(new ArrayList<DesignDataBook>());

		try {
			Files.walkFileTree(doc_dir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					DesignDataBook ddb=null;
					String excelFileName=file.toAbsolutePath().toString();
					if (excelFileName.endsWith(".xlsx")){
						System.out.println("[INFO]処理開始 ファイル名:\""+excelFileName+"\"");
						File f = new File(excelFileName);
						try{
							ddb=ApiBookReader.ReadExcel(f,file.getFileName().toString().replaceFirst("\\.xlsx$", ""));
							dbInsert(ddb);
/*
							designDataBooks.getDesignDataBookList().add(
									ApiBookReader.ReadExcel(f,file.getFileName().toString().replaceFirst("\\.xlsx$", ""))
									);*/
						} catch (IOException e){
							System.err.println("[ERROR]処理失敗 ファイル名:\""+excelFileName+"\"");
							e.printStackTrace();
						}
						System.out.println("[INFO]処理終了 ファイル名:\""+excelFileName+"\"");
					}

					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			System.err.println("[ERROR]\""+doc_dir.toString()+"\"検索中にエラーが発生しました。");
			e.printStackTrace();
			System.exit(1);
		}

//		System.out.println(designDataBooks);

//		dbInsert(designDataBooks);

//		designOutput(designDataBooks);

		System.out.println("[INFO]処理終了");
		System.exit(0);
	}

	public static void dbInsert(DesignDataBook ddb) {
		val emf = Persistence.createEntityManagerFactory("jpaEclipseLink");
		val em = emf.createEntityManager();

		List<DesignDataSheet> ddsl=ddb.getDesignDataSheetList();
		// ▼インサートデータ生成
		List<WorkProgramDesign> workProgramDesignList = new ArrayList<WorkProgramDesign>();
		List<FormDesign> formDesignList = new ArrayList<FormDesign>();
		List<ItemDesign> itemDesignList = new ArrayList<ItemDesign>();
		List<DetailDesign> detailDesignList = new ArrayList<DetailDesign>();

		WorkProgramDesign wpd;
		FormDesign formDesign;
		ItemDesign itemDesign;

		System.out.println("インサートデータ生成開始");

		for (DesignDataSheet dds: ddsl) {
			String sheetName=dds.getName();
			List<DesignData> ddl;
			ddl=dds.getDesignDataList();
			switch(sheetName) {
			case "画面デザイン":
				for(DesignData dd: ddl) {
					String designDataName=dd.getName();
					List<Map<String,String>> designTable;
					designTable=dd.getTable();
					switch(designDataName) {
					case "業務プログラム":
						wpd = new WorkProgramDesign();
						for (Map<String,String> dm: designTable) {
							for (Map.Entry<String, String> keyValue: dm.entrySet()) {
								switch (keyValue.getKey()) {
								case "業務プログラムid":
									wpd.setWorkProgramId(keyValue.getValue());
									break;
								case "業務プログラム名":
									wpd.setWorkProgramName(keyValue.getValue());
									break;
								case "パス":
									wpd.setCallPath(keyValue.getValue());
									break;
								case "初期表示画面id":
									wpd.setInitFormId(keyValue.getValue());
									break;
								default:
									break;
								}
							}
						}
						workProgramDesignList.add(wpd);
						break;

					case "画面":
						formDesign = new FormDesign();
						for (Map<String,String> dm: designTable) {
							for (Map.Entry<String, String> keyValue: dm.entrySet()) {
								switch (keyValue.getKey()) {
								case "画面id":
									formDesign.setFormId(keyValue.getValue());
									break;
								case "名前":
									formDesign.setFormName(keyValue.getValue());
									break;
								case "物理ファイル名":
									formDesign.setFileName(keyValue.getValue());
									break;
								default:
									break;
								}

							}
						}
						formDesignList.add(formDesign);
						break;

					case "項目":
						itemDesign = new ItemDesign();
						for (Map<String,String> dm: designTable) {
							for (Map.Entry<String, String> keyValue: dm.entrySet()) {
								switch (keyValue.getKey()) {
								case "画面id":
									itemDesign.setFormId(keyValue.getValue());
									break;
								case "項目id":
									itemDesign.setItemId(keyValue.getValue());
									break;
								case "項目名":
									itemDesign.setItemName(keyValue.getValue());
									break;
								case "コントロール種別":
									itemDesign.setControlType(keyValue.getValue());
									break;
								case "属性":
									itemDesign.setPropertyType(keyValue.getValue());
									break;
								case "桁数":
									// TODO itemDesign.setMaxLength(keyValue.getValue());  // 要調整
									break;
								case "変数名":
									itemDesign.setMappingName(keyValue.getValue());
									break;
								default:
									break;
								}

							}
						}
						itemDesignList.add(itemDesign);

						break;
					case "明細":
						break;
					default:
						break;
					}
				}
				break;
			case "イベント":
				ddl=dds.getDesignDataList();
				break;
			case "IF呼出":
				ddl=dds.getDesignDataList();
				break;
			case "DB対応表":
				ddl=dds.getDesignDataList();
				break;
			default:
				break;
			}
		}



		System.out.println("インサートデータ生成完了");
		// ▲インサートデータ生成

		// ▼データインサート
		val tx=em.getTransaction();
		try {
			tx.begin();
			System.out.println("トランザクション開始");
			Iterator<ItemDesign> it = itemDesignList.iterator();
			while(it.hasNext()) {
				em.persist(it.next());
			}
			tx.commit();
			System.out.println("トランザクションコミット");
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null && tx.isActive()){
				tx.rollback();
			}
			System.out.println("トランザクションロールバック");
		}
		// ▲データインサート
		em.close();
		emf.close();
	}

	public static void designOutput(DesignDataBooks ddb){

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "CLASSPATH");
		velocityEngine.setProperty("CLASSPATH.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();

		val vcontext = new VelocityContext();
		vcontext.put("name", "Velocity");
		vcontext.put("designDataBooks",ddb);

		// String Writerの生成
		val stringWriter=new StringWriter();

		// テンプレートの作成
		Template template=null;
		template = velocityEngine.getTemplate("SystemDesignData.vm");

		// テンプレートとデータのマージ
		template.merge(vcontext, stringWriter);
		stringWriter.flush();
		System.out.println(stringWriter.toString());
	}

}
