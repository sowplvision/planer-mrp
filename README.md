# PlanerMRP
Jest to aplikacja na system Android której celem jest symulacja planowania produkcji.
Planowanie to ma przede wszystkim uwgzledniać czas potrzebny na wyprodukowanie produktu na podstawie algorytmu MRP 
oraz oceniać koszta materiałów.

# Funkcje
Aplikacja, która pozwala:

- Przygotować plan produkcji wg algorytmu MRP uwzględniając czas potrzebny na wyprodukowanie produktu.

- Ocenić koszt oraz czas potrzebny do wyprodukowania wg przygotowanego planu przez aplikacje.

- Pobrać z bazy danych informacje o produktach które się tam znajdują oraz materiałach z których się składa wybrany produkt.

- Dodawać nowe produkty do bazy danych które składać się będą z wybieranych materiałów a ich koszt będzie sugerowany na podstawie kosztu pojedynczego materiału.

- Aplikacja będzie miała wspomagać decyzje dotyczące ceny produktu (obliczany będzie koszt materiałów potrzebnych do produkcji) oraz daty rozpoczęcia produkcji (wg czasu potrzebnego do wyprodukowania)

- Stworzyć plan oraz zapisać go w formie pliku Excel'owskich na urządzeniu mobilnym.

# Klasy

+ Panel główny @cr
W panelu głownym aplikacji użytkownik decyduje co chciałby zrobić w trakcie jej działania. Z niej może przejść do stworzenia nowego planu produkcji. Może dodać nowy materiał, półprodkt lub produkt oraz wyświetlić informacje.

+ Tworzenie planu
   - To właśnie tutaj użytkownik może stworzyć nowy plan działania. Do poszczególnych tabel może wpisać aktualny popyt, czy produkcję. Dostępny towar pobierany jest z bazy danych. Oprócz tego użytkownik ma możliwość stworzenia planu, co za tym idzie, zapisania go na telefonie w formacie csv.

+ Zapisywanie planu
   - Klasa związana z powyższą. Dzięki niej, można zapisać stworzony plan do pliku o rozszerzeniu csv.

+ Wpis do bazy
   - Właśnie tutaj użytkownik może dodać swój produkt, półprodukt lub materiał.

+ Obsługa bazy danych
   - Protokół obsługujący połączenie z baza danych PostGreSQL na serwerze v-ie.uek.krakow.pl.

+ Logowanie
   - Klasa zawierająca aktywność logowania się do bazy danych. Protokół połączeniowy ObsługaBazyDanych w celu połączenia się z bazą oraz sprawdzenia danych użytkownika.

+ Preferencje logowania:
   - Zawiera preferencje dotyczące logowania.

+ Preferencje:
   - Ustawienia ogólne.

+ Informacje:
   - Zawiera opis aplikacji, wersje, wymagania oraz nazwiska autorów.

# Autorzy 
Aplikacja wykonana przez studentów III roku, Informatyki Stosowanej z grupy KrDZIs3011Io.

- Daniel Kucharczyk 187086

- Mariusz Nowak 187617

