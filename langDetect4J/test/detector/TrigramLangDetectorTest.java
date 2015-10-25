package detector;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.Utils;

public class TrigramLangDetectorTest {

	@Test
	public void deLangDetectionTest() throws Exception {
		String detectedLangDE = new TrigramLangDetector()
				.detectLanguage("Er zählt zum Hof des Monarchen Beckenbauer und organisiert das Camp Beckenbauer, das jährliche Treffen des globalen Sportadels. Dort trifft man, wenn man Einlass erfährt, auch Fedor Radmann. Der jahrzehntelange Vertraute Beckenbauers hat beim ehemaligen Adidas-Chef Horst Dassler die Sportpolitik des Geben und Nehmens gelernt.");
		assertEquals(new Utils().DE_LANG, detectedLangDE);
	}

	@Test
	public void enLangDetectionTest() throws Exception {
		String detectedLangEN = new TrigramLangDetector()
				.detectLanguage("For weeks leading up to his announcement on Wednesday, Mr. Biden also repeatedly told advisers and potential campaign staff members that he did not believe Mrs. Clinton could defeat the Republican candidate. He watched as she played up her relationship with Mr. Obama, especially when speaking to black crowds in South Carolina and elsewhere, and argued that if anyone should take advantage of the sitting president’s record and high approval rating among Democratic primary voters, it should be him.");
		assertEquals(new Utils().EN_LANG, detectedLangEN);
	}

	@Test
	public void trLangDetectionTest() throws Exception {
		String detectedLangTR = new TrigramLangDetector()
				.detectLanguage("Meteoroloji Genel Müdürlüğü tarafından yapılan şiddetli yağış uyarısı Büyükşehir Belediyesi’ni de teyakkuza geçirdi. Başkan Menderes Türel’in talimatıyla İtfaiye Dairesi Başkanı Ahmet Kısa başkanlığında kriz masası oluşturuldu. Meydana gelebilecek ani sel ve su baskını gibi olumsuzluklara karşı gerekli tedbirler alındı. İtfaiye Daire Başkanlığı, Çevre Koruma ve Kontrol Dairesi Başkanlığı, Kırsal Hizmetler Daire Başkanlığı, Fen İşleri Daire Başkanlığı ve Antalya Su ve Atık Su İdaresi Genel Müdürlüğü (ASAT) ekipleri, olası olumsuzluklara karşı hazır bekletiliyor. Temizlik ekipleri, caddelerde yağmur suyu drenaj kanalları ağızlarını ve rögarları temizleyerek, su birikmesinin önüne geçmeye çalışıyor. ASAT ve itfaiye ekipleri, vidanjör ve kurtarma ekipleri alarmda bekliyor. 24 saat boyunca görev başında olacak kriz masası vatandaşlardan gelen ihbar ve taleplere anında cevap verecek.");
		assertEquals(new Utils().TR_LANG, detectedLangTR);
	}

}
