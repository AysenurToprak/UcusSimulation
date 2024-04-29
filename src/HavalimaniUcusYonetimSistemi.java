import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

class Uçak implements Comparable<Uçak> {
    int oncelik;
    String ucakId;
    int talepEdilenInisSaati;

    public Uçak(int oncelik, String ucakId, int talepEdilenInisSaati) {
        this.oncelik = oncelik;
        this.ucakId = ucakId;
        this.talepEdilenInisSaati = talepEdilenInisSaati;
    }

    // Uçakları önceliklerine ve talep edilen iniş saatlerine göre karşılaştır
    @Override
    public int compareTo(Uçak other) {
        if (this.oncelik != other.oncelik) {
            return Integer.compare(other.oncelik, this.oncelik); // Yüksek öncelik daha önde
        } else {
            return Integer.compare(this.talepEdilenInisSaati, other.talepEdilenInisSaati);
        }
    }

    // Getters ve setters
    // Gerekli diğer metotlar
}

public class HavalimaniUcusYonetimSistemi {
    public static void main(String[] args) {
        PriorityQueue<Uçak> inişPistiKullanimSirasi = new PriorityQueue<>();

        try {
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("");
                int oncelik = Integer.parseInt(line[0]);
                String ucakId = line[1];
                int talepEdilenInisSaati = Integer.parseInt(line[2]);
                Uçak uçak = new Uçak(oncelik, ucakId, talepEdilenInisSaati);
                inişPistiKullanimSirasi.add(uçak);
            }
            scanner.close();

            // output.txt dosyasını oluştur
            PrintWriter writer = new PrintWriter("output.txt");

            // Öncelikli kuyruktan uçakları al ve output.txt dosyasına yaz
            while (!inişPistiKullanimSirasi.isEmpty()) {
                Uçak uçak = inişPistiKullanimSirasi.poll();
                writer.println(uçak.oncelik + " " + uçak.ucakId + " " + uçak.talepEdilenInisSaati + " " +
                        uçak.talepEdilenInisSaati + " 0 " + (uçak.talepEdilenInisSaati + 1)); // İniş saati ve gecikme süresi olarak talep edilen saat, kalkış saati olarak ise bir sonraki saat eklendi.
            }

            writer.close();
            System.out.println("output.txt dosyası oluşturuldu.");

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + e.getMessage());
        }
    }
}