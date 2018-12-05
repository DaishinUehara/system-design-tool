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
import java.util.List;

import javax.persistence.Persistence;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import lombok.val;
import uehara.daishin.sdtool.apireader.ApiBookReader;
import uehara.daishin.sdtool.db.ItemDesign;
import uehara.daishin.sdtool.design.DesignDataBook;
import uehara.daishin.sdtool.design.DesignDataBooks;



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

		DesignDataBooks designDataBooks=new DesignDataBooks();
		designDataBooks.setName("テスト設計書");
		designDataBooks.setDesignDataBookList(new ArrayList<DesignDataBook>());

		try {
			Files.walkFileTree(doc_dir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String excelFileName=file.toAbsolutePath().toString();
					if (excelFileName.endsWith(".xlsx")){
						System.out.println("[INFO]処理開始 ファイル名:\""+excelFileName+"\"");
						File f = new File(excelFileName);
						try{
							designDataBooks.getDesignDataBookList().add(
									ApiBookReader.ReadExcel(f,file.getFileName().toString().replaceFirst("\\.xlsx$", ""))
									);
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

		System.out.println(designDataBooks);

		dbInsert(designDataBooks);

		designOutput(designDataBooks);

		System.out.println("[INFO]処理終了");
		System.exit(0);
	}

	public static void dbInsert(DesignDataBooks ddb) {
		val emf = Persistence.createEntityManagerFactory("jpaEclipseLink");
		val em = emf.createEntityManager();
		// ▼インサートデータ生成
		System.out.println("インサートデータ生成開始");
		List<ItemDesign> itemDesignList = new ArrayList<ItemDesign>();

		ItemDesign itemDesign=new ItemDesign();
		itemDesign.setItemName("testItem");
		itemDesign.setItemId("item001");

		itemDesignList.add(itemDesign);

		// ▼データインサート
		val tx=em.getTransaction();
		try {
			tx.begin();
			System.out.println("トランザクション開始");
			em.persist(itemDesign);
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
