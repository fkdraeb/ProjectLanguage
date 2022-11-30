package com.upskill.TaskManager;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.langdetect.optimaize.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;

import java.io.IOException;
import java.net.URL;

public class TikaTester {

    static String html = "<html> <img> <src>";
    static String alemao = "Eine Blume ist die bestäubungsbiologische Einheit der Blütenpflanzen. Es ist ihre Aufgabe, Bestäuber anzulocken, oder sie bildet eine technisch-funktionelle Einheit. Blume ist also ein ökologisch-funktional definierter Begriff. So werden von Tieren bestäubte Blumen nach ihren Bestäubern eingeteilt, etwa die Vogelblumen oder die Käferblumen.";

    static String frances = "En biologie, chez toutes sortes de plantes à fleurs (angiospermes), la fleur constitue l'organe de la reproduction sexuée et l'ensemble des « enveloppes » qui l'entourent.";
    static String galego = "Todas as espermatofitas posúen flores que producirán sementes, pero a organización interna da flor é moi diferente nos dous principais grupos de espermatofitas: as ximnospermas viventes e as anxiospermas. O grupo das anxiospermas, con máis de 250.000 especies, é unha liñaxe evolutivamente exitosa que conforma a maior parte da flora terrestre existente. A flor de anxiosperma é o carácter definitorio do grupo e é, probablemente, un factor clave no seu éxito evolutivo. É unha estrutura complexa, cuxo plan organizacional está conservado en case todos os membros do grupo, aínda que presenta unha tremenda diversidade na morfoloxía e fisioloxía de todas e cada unha das pezas que a compoñen. A base xenética e adaptativa de tal diversidade está a comezar a comprenderse en profundidade,[4] así como tamén a súa orixe, que data do cretáceo inferior, e a súa posterior evolución en estreita interrelación cos animais que se encargan de transportar os gametos.\n" +
            "Con independencia dos aspectos sinalados, a flor é un obxecto importante para os seres humanos. A través da historia e das diferentes culturas, a flor sempre tivo un lugar nas sociedades humanas, xa sexa pola súa beleza intrínseca ou polo seu simbolismo. De feito, cultivamos especies para que nos provean flores desde hai máis de 5000 anos e, actualmente, esa arte transformouse nunha industria en continua expansión: a floricultura.";
    static String espanhol = "La flor es la estructura reproductiva característica de las plantas llamadas espermatofitas o fanerógamas. La función de una flor es producir semillas a través de la reproducción sexual. Para las plantas, las semillas son la próxima generación y sirven como el principal medio a través del cual las especies se perpetúan y se propagan.";
    static String holandes = "Een bloem is het deel van een plant waarin de organen voor geslachtelijke voortplanting bij elkaar staan.";
    static String runaSumi = "Tuktuqa pawqar kaptin, palamakunam tuktup ñupchunta suq'uq hamuspa sisata huk tuktumanta huktaq tuktuman apamunku, chaywanmi ruru raphipi kaq runtuchakunata sisachaspa. Huk muruyuq yurakunapiqa wayram sisata apaykun. Chaymantataq musuq murukunayuq rurukunam puquyta atinku.";
    static String curdo = "Kulîlk an çîçek formeke riwekê ye. Kulîlk organên nûvejîna, hilberîna riwekan dihewînin.";
    static String hebraico = "פרח הוא איבר הרבייה בצמחים מכוסי-זרע. מבנה זה מכיל איברי רבייה זכריים או נקביים המשמשים לרבייה זוויגית ולעיתים לרבייה אל-זוויגית.\n" +
            "\n";
    static String turco = "Çiçek bitkilerde üremeyi sağlayan organları taşıyan yapı. Bir çiçek, 4 kısımdan oluşur.\n" +
            "\n" +
            "Üreme organlarını dıştan sararak onları dış etkilerden korur. Doğrudan üremeye katılmadığı için 'verimsiz kısım' olarak da adlandırılır.";

    static String ucraniano = "видозмінений укорочений, нерозгалужений пагін з обмеженою здатністю росту, метаморфізованими листками, призначений для запилення, статевого процесу і утворення насіння та плодів, що формується у квіткових рослин.\n" +
            "\n" +
            "Квітка — складна система органів, що забезпечує насінневе розмноження покритонасінних (квіткових) рослин. У двостатевій квітці проходять мікро- і мегаспорогенез, мікро- і мегагаметогенез, запилення, запліднення, розвиток зародка, утворення плоду з насінням. Поява квітки в процесі еволюції — ароморфоз, який забезпечив широке розселення покритонасінних на Землі.";

    static String mandarim = "嘅生殖用構造：一樖開花植物生到咁上下，就會開花；喺多數用蟲做傳粉媒介嘅開花植物當中，一朵花會同時有齊雄蕊（stamen）同雌蕊（carpels）[註 1]，雄蕊會生產花粉（pollen），雌蕊會生產胚珠（ovule）；蟲媒花通常會分泌花蜜，靠噉嚟吸引蜜蜂同蝴蝶等嘅昆蟲走入花嗰度採蜜，當有隻蟲做呢樣嘢嗰陣，花粉會黐上佢身，然後等隻蟲飛去第朵同種嘅花採蜜嗰陣，花粉就有機會掂到另外嗰朵花嘅雌蕊，做受粉（fertilization）產生孕育下一代嘅種子以及令胚珠發育成果實，令個植物物種能夠繁衍後代[註 2][1]。喺呢個過程當中，開花植物進行緊有性繁殖－靠公同乸兩種配子嘅結合，產生出喺遺傳上似父母、但唔係同父母完全一樣嘅下一代[2][3]。";

    static String cantones = "花是被子植物的繁殖器官，其生物学功能的是结合雄性精细胞与雌性卵细胞以产生种子。这一进程始於传粉，然後是受精，从而形成种子并加以传播。对於高等植物而言，种子便是其下一代，而且是各物种在自然分布的主要手段。同一植物上着生的花的组合称为花序。";

    static String mirandes = "Acudi-me, moças Beni-me acudir Las pulgas son tantas Nun me déixan drumir Bóto-me ha agarrá-las Bótan-se a fugi Bóto-me ha agarrá-las Bótan-se a fugir Este pandeiro que toco Ren un aro de cortiça You toco an Dues Eigreijas Respónden na Belariça" ;

    static String japones = "3年ぶりに会場開催が決まった「K-BOOKフェスティバル2022」では、会場運営をお手伝いいただくボランティアを募集いたします。\n" +
            "ぜひ、皆さんの力でフェスティバルを一緒に盛り上げてください！";

    public static void identifica() throws IOException, TikaException {
        URL meuURL2 = new URL("https://etext.old.no/Bugge/hymis.html");
        Tika tika = new Tika();
        String content = tika.parseToString(meuURL2);
        LanguageDetector detector = new OptimaizeLangDetector().loadModels();
        LanguageResult result = detector.detect(content);
        //detector.detect().getLanguage();
        System.out.println("Idioma:    " + result.toString());

    }
}
