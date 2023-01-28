/*
 * @file Main.java 
 * @description Kullanıcıdan alınan parametreler doğrultusunda oluşturulan matrisin çeperini girilen adım ve yöne göre çevirir.  
 * @date 16.12.2020 
 * @author Ömer Kerem Adalı
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String secenek = "";
        int matrisParametre[] ={5,5,-1,100,2}; // oluşturulacak matris ve arrayin döndürülme özelliklerini içinde tutan bir array
        boolean olusturulduMu = false; // matris oluşturulmadan uygulamanın çalıştırılmasını engelleyen bir boolean değişken
        int[][] matris={};
        
        while(!secenek.equals("5")) { // seçenek 5 olarak seçilmesi durumunda switch-case'e hiç girilmeden döngüden çıkılıyor ve iyi günler yazısı ile uygulama kapanıyor
            System.out.println("***************************");
            System.out.println("* 1. Uygulamayı Çalıştır  *");
            System.out.println("* 2. Matris Oluştur       *");
            System.out.println("* 3. Dönüş Yönü Gir       *");
            System.out.println("* 4. Adım Sayısı Gir      *");
            System.out.println("* 5. Çıkış                *");
            System.out.println("***************************");
            
            System.out.println("");
            System.out.print("Seçenek: ");
            secenek = scanner.nextLine();
            
            while(!secenek.equals("1")&&!secenek.equals("2")&&!secenek.equals("3")&&!secenek.equals("4")&&!secenek.equals("5")) {
                System.out.println("Geçersiz Seçenek..."); // bu döngü secenek değişkeni ile kullanıcıdan alınan değerin doğruluğunu kontrol ediyor
                System.out.print("Seçenek: "); // eğer geçerli bir seçenek girilmediyse döngü devam edip kullanıcıdan tekrar değer istiyor
                secenek = scanner.nextLine();
            }
            
            switch (secenek) {
                case "1":
                    if(olusturulduMu) {
                        uygulamaCalistir(matrisParametre, matris); //tüm methodlar üzerinde oynama yapmak için matrisParametre arrayini alır. Döndürme işlmei için bu methodda matris de alınır.
                    }
                    else {
                        System.out.println("Lütfen Önce Matrisi Oluşturun...");
                        System.out.println("");
                    }
                    break;
                case "2":
                    matris = matrisOlustur(matrisParametre); //methoddan dönen matris, uygulama çalıştırdan kullanılmak üzere atanır.
                    olusturulduMu = true; // matris oluşturulduktan sonra uygulamanın çalıştırılabilmesi için kullanılacak methoda giriş hakkı sağlanır.
                    break;
                case "3":
                    matrisDonusYonu(matrisParametre);
                    break;
                case "4":
                    matrisAdimSayisi(matrisParametre);
                    break;
            }
        }
        
        System.out.println("İyi Günler...");
   
    }
    
    public static void uygulamaCalistir(int[] matrisParametre,int[][] matris) {
        int ceper[] = new int[(2*matris.length+2*matris[0].length)-4]; //ceper arrayinin uzunluğu olarak girilen değer herhangi bir arrayin ceperinde bulunan eleman sayısının hesabı için bir denklem
        
        int x = 0; 
        int y = 0;
        int indeks = 0;
        
        while(y != matris[0].length) { //while döngüleri ile başta verilen arrayin çeperi 00 indeksinden başlanarak ceper arrayine kayıt ediliyor.
            ceper[indeks] = matris[x][y]; 
            y++; // y satırın son elemanın indeksine eşit olana kadar artıyor
            indeks++; // indeks değişkeni ceperin indeksi için kullanılıyor
        }
        y--; // while döngüs son atamadan sonra y ve indeksi bir kere daha arttıracağından bu gereksiz arttırma döngü çıkışından azaltılarak hata durumu önleniyor.
        indeks--;
        
        while(x != matris.length) {
            ceper[indeks] = matris[x][y];
            x++; //satirin son indeksine gelindikten sonra sutun sonuna gidilmek için x arttiriliyor.
            indeks++;
        }
        x--;
        indeks--;
        
        while(y != -1) { // y sıfıra eşit oluncaya kadar y azaltılıyor buda matris ceperinin alt kısmınının arraya kaydedilmesini sağlıyor.
            ceper[indeks] = matris[x][y];
            y--;
            indeks++;
        }
        y++;
        indeks--;
        
        while(x != 0) { // x bire eşit oluncaya kadar da x azaltılıyor. 0 değil 1 çünkü 00 indeksi birince while döngüsünde zaten alınmıştı
            ceper[indeks] = matris[x][y];
            x--;
            indeks++;
        }
        x++;
        indeks--;
            
        int kaydirma = matrisParametre[4]; // ceper arraya atandıktan sonra adım sayısı kaydirma adlı int değere ataniyor
        
        if (kaydirma > (2*matris.length+2*matris[0].length)-4) { // eğer girilen kaydirma sayisi ceperin bir tam tur attacağı uzunluktan uzun ise kodun doğru çalışması için ekstra olan kısmı atar
            while (kaydirma > (2*matris.length+2*matris[0].length)-4) {
                kaydirma -= (2*matris.length+2*matris[0].length)-4; // kaydirma değişkeni bir tam tur için gereken adım sayısından fazla olduğu sürece bir tam tur için gerek sayı kaydirmadan çıkartılır.
            }
        }
        
        if(matrisParametre[2] == 1) {
            kaydirma = (2*matris.length+2*matris[0].length)-4 - kaydirma; //ceper saat yönünün tersinde döndürülmek istenirse kaydirma değeri formülden çıkartılıp kendisine atanır bunu sebebi raporda değinildi
        }
        
        
        x = 0;
        y = 0;
        kaydirma = kaydirma * -1; //kaydrima değişkeninin gireciği matematiksel işlemler sürecinde negatif olması işleri kolaylaştırıyor raporda detaylıca değinildi.
            
        while(y != matris[0].length) {
            if(kaydirma == 0) { // buradaki dört döngünün amacı başta çeperi aldığımız gibi 00 kordinatından başlayarak matrisin çevresini dönmek. 
                kaydirma = ceper.length*-1;
            }
            //kaydirma miktari - olarak alınıyor her döngü başında kontrol edilip 0'a eşit olması durumunda arrayın kalan kısmının yazılması için ceper.length'in negatif değerine eşit oluyor
            matris[x][y] = ceper[ceper.length+kaydirma]; 
            y++;
            kaydirma++;             
        }
        y--; //önceki döngülerdeki gibi esktra döngüden gelen değerler döngü sonunda çıkartılıyor
        kaydirma--;
            
        while(x != matris.length) {
            if(kaydirma == 0) {
                kaydirma = ceper.length*-1;
            }
            matris[x][y] = ceper[ceper.length+kaydirma];
            x++;
            kaydirma++; 
        }
        x--;
        kaydirma--;
            
        while(y != -1) {
            if(kaydirma == 0) {
                kaydirma = ceper.length*-1;
            }
            matris[x][y] = ceper[ceper.length+kaydirma];
            y--;
            kaydirma++;
        }
        y++;
        kaydirma--;
            
        while(x != 0) {
            if(kaydirma == 0) {
                kaydirma = ceper.length*-1;
            }
            matris[x][y] = ceper[ceper.length+kaydirma];
            x--;
            kaydirma++;
        }
        x++;
        kaydirma--;
        
        
        System.out.println(""); // matrisin yeni hali kullanıcıya gösteriliyor.
        System.out.println("Matrisin Yeni Hali:");
        for(int i = 0 ; i < matris.length ; i++) {
            for(int j = 0 ; j < matris[0].length ; j++) {
                System.out.print(matris[i][j]+" ");
            }
            System.out.println("");
        }
         
    }
    
    public static int[][] matrisOlustur(int[] matrisParametre) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Satır Boyutu: ");
        String satir = scanner.nextLine();
        while(true) { //bu döngü kullanıcıdan alınan değerin önce int olduğuna bakıyor eğer int ise 1'den büyük olma duruma bakıyor değil ise kullanıcıdan yeni değer istiyor.
            if(intMi(satir)) {
                if(stringToInt(satir) > 1) { // 2 boyutlu bir array için değerlerin 2 veya 2'den büyük olması gerekiyor.
                    break;
                }
                else { //int değil ise yine kullanıcıdan yeni değer isteyip döngünün başına dönüyor.
                    System.out.println("Geçersiz Satır Boyutu...");
                    System.out.print("Satır Boyutu: ");
                    satir = scanner.nextLine();
                }
            }
            else {
                System.out.println("Geçersiz Satır Boyutu...");
                System.out.print("Satır Boyutu: ");
                satir = scanner.nextLine();
            }
        }
        matrisParametre[0] = stringToInt(satir);
        
        System.out.print("Sütun Boyutu: ");
        String sutun = scanner.nextLine();
        while(true) { // aynı işlem sütun için de gerçekleştiriliyor.
            if(intMi(sutun)) {
                if(stringToInt(sutun) > 1) {
                    break;
                }
                else {
                    System.out.println("Geçersiz Sütun Boyutu...");
                    System.out.print("Sütun Boyutu: ");
                    sutun = scanner.nextLine();
                }
            }
            else {
                System.out.println("Geçersiz Sütun Boyutu...");
                System.out.print("Sütun Boyutu: ");
                sutun = scanner.nextLine();
            }
        }
        matrisParametre[1] = stringToInt(sutun);
        
        System.out.print("Aralık Sonu: ");
        String aralikSonu = scanner.nextLine();
        while(true) { // 0-1 arasında bir tam sayı olmadığı için aynı işlem aralık sonu için de geçerli
            if(intMi(aralikSonu)) {
                if(stringToInt(aralikSonu) > 1) {
                    break;
                }
                else {
                    System.out.println("Geçersiz Aralık Sonu Boyutu...");
                    System.out.print("Aralık Sonu: ");
                    aralikSonu = scanner.nextLine();
                }
            }
            else {
                System.out.println("Geçersiz Aralık Sonu Boyutu...");
                System.out.print("Aralık Sonu: ");
                aralikSonu = scanner.nextLine();
            }
        }
        matrisParametre[3] = stringToInt(aralikSonu);
        
        int[][] matris = new int[matrisParametre[0]][matrisParametre[1]]; //matrisin ölçüleri parametreye kaydediliyor.
        
        System.out.println("");
        System.out.println("Oluşturulan Matris:");
        for(int x = 0 ; x < matris.length ; x++) {//verilen değerler doğrultusunda matris oluşturulup kullanıcıya gösteriliyor.
            for(int y = 0 ; y < matris[x].length ; y++) {
                matris[x][y] = 1+(int)((Math.random() * (matrisParametre[3]-1)));
                System.out.print(matris[x][y]+" ");
            }
            System.out.println("");
        }  
        return matris;
    }
    
    public static void matrisDonusYonu(int[] matrisParametre) {
        Scanner scanner = new Scanner(System.in);      
        System.out.println("1 Saat Yönünün Tersi");
        System.out.println("-1 Saat Yönü");
        System.out.print("Yön: ");
        String yon = scanner.nextLine();
        
        while(!yon.equals("1") && !yon.equals("-1")) { // geçerli olabilecek 1 ve -1 seçeneklerini kontrol eder bunlar dışında ise kullanıcıdan yeni değer ister
            System.out.println("Geçersiz Yön Girdiniz...");
            System.out.print("Yön: ");
            yon = scanner.nextLine();
        }
        matrisParametre[2] = stringToInt(yon); // girilen değeri integera çevirip kaydeder
    }
    
    public static void matrisAdimSayisi(int[] matrisParametre) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Adım Sayısı: ");
        String adimSayisi = scanner.nextLine();
        
        while(!intMi(adimSayisi)) { // alınan değerin int olduğu ve pozitif olduğunu kontrol eder.
            System.out.println("Geçersiz Adım Sayısı...");
            System.out.print("Adım Sayısı: ");
            adimSayisi = scanner.nextLine();
        }
        matrisParametre[4] = stringToInt(adimSayisi); // alınan değer integera dönüştürülüp paramtere değerine atanır.
    }
    
    public static int stringToInt(String sayi) {
        int sonuc = 0;        
        for(int x = 0 ; x < sayi.length() ; x++) { //döngü girilen string ifadenin her karakterinde dolanır
            sonuc *= 10; //döngü her döndüğünde 10 ile çarparak baştaki sayıdan sondaki sayıya doğru basamak farkını oluşturur
            sonuc += (sayi.charAt(x)-48); // charAt ile alınan char toplama işlemine sokulduğunda inte cast edildiğinden bu karakterin kodu toplama işleminde yer alacak bunun önüne geçmek için 0 karakterinin kodu olan 48'i çıkartıyoruz
        }
        return sonuc;     
    }
    public static boolean intMi(String ifade) {
        int intMi = 0; //intMi methodu kullanıcıdan alınan string değerin tamamının integer bir değer olup olmadığına bakar
        for(int x = 0 ; x < ifade.length() ; x++) { // ilk döngü karakterlerin üzerinde dolaşır
            for(int y = 0 ; y <= 9 ; y++) { // ikinci döngü üzerinde bulunan karakterin 0-9 olup olmadığına bakar eğer bu sayılardan biriyse intMi değeri arttırılır.
                if(ifade.charAt(x) == (char)(48+y)) { //48 değeri '0' karakterinin chara castlenmiş halidir 1 = 49, 2 = 50 şeklinde gittiği için 48 değerinin üzerine y değeri eklenir ve öyle chara castlenir.
                    intMi++; // fonksiyon - karakterini int saymadığı için negatif değerleri de false döndürür bu kullanıcıdan negatif değer istemediğimiz için gayet kullanışlı bir durum.
                }
            }
        }
        
        if (intMi == ifade.length()) { // girilen ifadenin her karakteri bir sayı ise karakterin uzunluğunda intMi değişkeni arttırılmış olacağından true döndürülür.
            return true;
        }
        else { // girilen ifade uzunluğunda değil ise false döndürülür.
            return false;
        }
    }
}