
    1. Predlog teme

Ovaj projekat sluzi za rezervisanje karate za bioskop. Karte se rezervisu na nalog korisnika I na kraju se nakon pravljenja rezervacije, potvrdjuje I unosi u bazu
   
    2. Opis funkcionalnosti

Aplikacija prvo pita Korisnika za username I password. Nakon toga, ako je nalog korisnicni prelazi se na pocetnu stranu, ako je admin nalog, onda se prelazi na stranu sa admin komandama. Ako se korisnik ulogovao, dobija mogucnost, prvo da izabere film. Moze da se pretrazi po zanru I po tehnologiji, a moze I odmah sve da se prikaze. Kada se izabere, dalje se ide na odabir projekcije. Tu se mogu staviti filteri za datum I za lokaciju projekcije. Nakon toga se ide na stranicu za odabir mesta nakon cega je rezervacija gotova. Admin ima mogucnosti da unosi, brise I menja filmove I projekcije. 
    
    3. Struktura aplikacije

Program ima 23 klase koje rade na funkcionalnosti, 3 css klase I 3 test klase. Ima 10 klasa sa GUI od kojih 5 radi sa adminom I 5 sa korisnikom. Ostale klase su pomocne klase. Sadrze pravila, liste,rad sa bazom podataka,  klase objekata, I u njima se cuvaju podaci pre nego sto korisnik potvrdi da zeli rezervaciju pri cemu se unosi sve u bazu podataka. Klase su medjusobno povezane jedna preko druge, osim u poslednjoj klasi Mesto, gde uzimamo podatke koje smo cuvali u ostalim klasa da bismo rezervaciju potvrdili. Takodje, radio sam sa bibliotekama poput mysql java connector, jsoup, jfxtras I tornadofx. Test klase testiraju pouzdanost preuzetih podataka.


    4. Korisničko uputstvo


Korisniku nakon povezivanja na bazu, iskace meni za login. Treba se upisati username I password
![image](https://user-images.githubusercontent.com/67857389/176937269-31a21123-9745-493d-9a30-c6a109b43be1.png)


Ako korisnik ima akaunt, kuca podatke I loguje se, ako nema ide na registracija I pravi akaunt
![image](https://user-images.githubusercontent.com/67857389/176937312-49c597d0-a8e7-4d9a-8312-842b5324f2a7.png)

Nakon toga moze da se uloguju I ide na pocetnu stranu

Ako je korisnik admin, ukuca svoje podatke I  umesto na pocetnu ide na meni na kome bira sta zeli da uradi sa podacima.
![image](https://user-images.githubusercontent.com/67857389/176937321-a845521d-7c42-40fe-b1ce-6b2f8a8a7c20.png)

Unos novog filma: 

![image](https://user-images.githubusercontent.com/67857389/176937346-df8db98c-8779-4aef-a040-12be46a3b241.png)

Redosled polja za upis su jednaka poljima iz tabele. 

Izmena postojecih filmova: 
![image](https://user-images.githubusercontent.com/67857389/176937359-c34ed149-e534-41ef-96e0-eae6a113d314.png)


Admin na dvoklik moze da prebaci podatke iz tabele u field-ove, I nakon toga kada izmeni klikne na update da update-uje, a da bi obrisao zapis, klik na zapis I obrisi zapis. Isti je postupak za projekcije.
![image](https://user-images.githubusercontent.com/67857389/176937404-4094e278-aca2-4538-8870-5dca4fc796c5.png)
![image](https://user-images.githubusercontent.com/67857389/176937408-070a7484-dc11-4404-a0be-823114856af1.png)


Ako korisnik nije bio admin, ovako ce mu izgledati pocetni ekran:
![image](https://user-images.githubusercontent.com/67857389/176937422-aa403078-5434-4a04-9e20-95fc03f618bd.png)

Gore su filteri za tehnologije I zanrove. Nakon odabira filtera, korisnik preba kliknuti na Prikazi rezultate I dobice rezultate, Nakon toga klikne na rezultat u tabeli I dugme Izaberi ce da odvesti dalje. Ako korisnik sumnja da film koji je zeleo da gleda vise nije u prikazivanju, moze da pogleda arhivu filmova.

![image](https://user-images.githubusercontent.com/67857389/176937511-5fa26b6a-020e-4d40-9904-4f80c97ff3cf.png)


Nakon odabira filma dobija se prikaz svih dostupnih projekcija. Projekcije se mogu filtrirati po datumu I lokaciji I onda klikom na dugme pretrazi po filteru. Nakon sto korisnik odluci koju projekciju zeli, moze kliknuti na projekciju u tabeli I onda na izaberi projekciju
![image](https://user-images.githubusercontent.com/67857389/176937527-06be4394-de9b-4ceb-989b-8233cc3431d4.png)


To ce ga dovesti do zadnjeg prozora. Korisnik ovde bira sediste klikom na krug I potvrdjuje rezervaciju. Korisnik uvek moze da se vrati nazad ako zeli da izmeni neke podatke


    5. Zaključak

Cilj projekta je bilo prikazivanje naucenog preko rezervacije karata za bioskop. Voleo bih da mogu da ulepsam aplikaciju ali otom potom
