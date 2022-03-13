# Projek-zaliczeniowy SEM3-JAVA

## Polecenie:
**Zbuduj klasę Gwiazda posiadającą następujące pola:**

**nazwa** – dowolne oznaczenie gwiazdy. Przyjmujemy, iż nazwa każdej
gwiazdy składa się z 3 dużych liter oraz 4 cyfr.

**nazwa katalogowa** – nazwa katalogowa składa się litery alfabetu
greckiego oraz nazwy gwiazdozbioru. Najjaśniejsza gwiazda w
gwiazdozbiorze oznaczana jest jako alfa, kolejna jako beta i tak
dalej. Na potrzeby projektu zakładamy, iż kolejne litery greckie
nadawane są gwiazdom w takiej kolejności, w jakiej dodane zostały
do gwiazdozbioru. Przykładowo, jeżeli w bazie istnieją 2 gwiazdy w
gwiazdozbiorze Wolarza, to trzecia z nich otrzyma katalogową nazwę
gamma Wolarza (nazwa składa się z litery greckiej oraz nazwy
gwiazdozbioru, do którego została dodana).

**deklinacja** – jedna ze współrzędnych astronomicznych przyjmująca
wartości od 0 do 90 stopni dla gwiazd znajdujących się na półkuli
północnej oraz 0 do -90 stopni dla gwiazd na półkuli południowej.
Wartość podajemy jako xx stopni yy minut zz.zz sekund.

**rektascensja** – druga współrzędna astronomiczna przyjmująca
wartości od 00h do 24h. Wartość podajemy jako xx h yy m zz s.

**obserwowana wielkość gwiazdowa** – wielkość stosowana do określenia
blasku gwiazd wyrażana w jednostkach magnitudo. Niższa wartość
oznacza większą jasność gwiazdy. Zakładamy, iż minimalna
dopuszczalna wielkość gwiazdowa wynosi -26.74 (wartość dla
Słońca). Przyjmujemy, iż maksymalna wartość magnitudo wynosi
15.00.

**absolutna wielkość gwiazdowa** – wartość magnitudo, jaką ma
gwiazda z określonej odległości. Istnieje ścisła zależność pomiędzy
obserwowaną a absolutną wielkością gwiazdową wyrażona wzorem:
M = m − 5· log10r + 5 (logarytm przy podstawie 10 z r), gdzie m to
obserwowana wielkość gwiazdowa, a r to odległość od gwiazdy
wyrażona w parsekach. Przyjmujemy, iż 1 parsek to 3.26 roku
świetlnego.

**odległość w latach świetlnych.**

**gwiazdozbiór** – gwiazdozbiór, w którym można zobaczyć daną
gwiazdę.

**półkula PN/ PD** – półkula na której można zobaczyć daną gwiazdę.

**temperatura (podana w stopniach celsjusza).** Przyjmujemy, iż
minimalna temperatura gwiazdy wynosi 2000 stopni, górna granica
nie występuje.

**masa** (podana w odniesieniu do masy Słońca). Przyjmujemy, iż
minimalna masa gwiazdy wynosi 0.1 masy Słońca, natomiast
maksymalna dopuszczalna masa wynosić będzie 50

W przypadku dodawania nowego obiektu należy uwzględnić wszystkie
podane powyżej parametry, ich zakres oraz ewentualne zależności
pomiędzy nimi.
Oprócz dodania nowej gwiazdy możemy również wyświetlić wszystkie
gwiazdy w bazie, usunąć obiekt o wybranej nazwie katalogowej. W
przypadku usunięcia np. gwiazdy beta w danym gwiazdozbiorze, należy
zadbać, o to, aby wszystkie pozostałe nazwy katalogowe zostały
uaktualnione. Np. po usunięciu gwiazdy alfa Ryb, wszystkie pozostałe
gwiazdy w gwiazdozbiorze są aktualizowane, tj. beta Ryb na alfa Ryb,
gamma Ryb na beta Ryb i tak dalej.

**W bazie możliwe jest wyszukiwanie obiektów na podstawie następujących
kryteriów:**

-wyszukaj wszystkie gwiazdy w gwiazdozbiorze;

-wyszukaj gwiazdy znajdujące się w odległości x parseków od Ziemii
(należy uwzględnić iż obiekt gwiazdowy opisany jest przy pomocy lat
świetlnych);

-wyszukaj gwiazdy o temperaturze w zadanym przedziale;

-wyszukaj gwiazdy o wielkości gwiazdowej w zadanym przedziale;

-wyszukaj gwiazdy z półkuli północnej / południowej;

-wyszukaj potencjalne supernowe. Supernowe to gwiazdy, których
masa przekracza tzw. granicę Chandrasekhara, która wynosi 1.44
masy Słońca.

Wszystkie dane powinny zostać zapisane w pliku obiektowym.
Poszczególne elementy projektu powinny być zrealizowane jako funkcje,
lub metody klasy.

