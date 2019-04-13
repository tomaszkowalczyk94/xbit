# xbit


XBit to biblioteka mający za zadanie ułatwić operacje bitowe w języku Java, która interpretuje liczby 
całkowite w kodzie dopełnień do dwóch i nie jest możliwa interpretacja ich w naturalnym 
kodzie binarnym (w skrócie NKB). Z tego powodu powstał moduł XBit implementujący taką operację. 
Potrafi on odczytać liczbę n-bitową i zwrócić jej wartość jako zmienną typu int, interpretując
ją jako bity w U2 lub NKB.

Java jest językiem programowania wysokiego poziomu, 
  kompilowanym do kodu bajtowego. Z tego powodu nie jest on zazwyczaj 
  stosowany w emulacji, gdyż kod emulatora musi być uruchamiany w maszynie wirtualnej, 
  co nie jest wydajnym rozwiązaniem.

Bibliotekę zaprojektowano w taki sposób, aby była jak najbardziej uniwersalna,
 i można ją było **wykorzystać do budowy emulatora Zilog-a Z80, ale także innych procesorów.**`


Innym poważnym problemem języka Java jest brak typów prostych pozwalających przechowywać
wyłącznie liczby dodatnie. Problem rozwiązuje biblioteka XBit. Zawiera ona klasy, które
umożliwiają przechowywanie wartości jedno i dwu bajtowych. Mogą one zostać zinterpretowane 
jako liczby zapisane w kodzie dopełnień do dwóch (liczby dodatnie i ujemne), lub naturalnym kodzie 
binarnym (tylko liczby dodatnie).  
Przykładowo, liczba binarna 1110 odczytana w naturalnym kodzie binarnym (NKB),
to 15 (w zapisie decymalnym), natomiast w kodzie dopełnień do dwóch (U2) to -2. XBit 
pozwala na obydwie interpretacje za pomocą odpowiednich metod, zwracając wynik jako typ prymitywny int. 
	
Nie jest to idealne rozwiązanie. Typ int przechowuje cztero-bajtowe liczby z zakresu od -2 147 483 648 do 2 147 483 647,
więc większość bajtów zostaje niewykorzystana. Nadmiarowość jest w tym przypadku wymagana,
ponieważ zakres liczb w notacji NKB i~w~U2 jest inny.
	
Przykładowo, w przypadku gdy za pomocą XBit stworzono reprezentację liczby ośmio bitowej:  
1111 0000 (w kodzie jest to NKB=240, U2=-16) to wywołując metodę interpretującą ją jako liczba bez znaku 
(czyli w notacji NKB), zostanie zwrócona zmienna o typie prymitywnym int, o wartości 240,  binarne  
00000000 00000000 00000000 11110000.   
Natomiast jeśli wykonamy metodę interpretującą ją jako liczba ze znakiem (czyli w notacji U2) 
zostanie zwrócona wartość -16, binarnie  
11111111 11111111 11111111 11110000.

    
 
## Możliwości biblioteki XBit
 * reprezentacja liczb 8 i 16 bitowych,
 * interpretacja liczb w naturalnym kodzie binarnym lub dopełnieniu do dwóch,
 * operacje na pojedynczych bitach (możliwość zmiany, odczytu bitu na danej pozycji),
 * opcja odczytania grupy bitów (odczytanie kilku bitów z~określeniem pozycji pierwszego i ostatniego bitu),
 * interpretacja liczb 16 bitowych w formacie \emph{big endian} lub \emph{little endian},
 * operacje arytmetyczne (dodawanie, odejmowanie),
 * operacje bitowe na liczbach (negacja, alternatywa, koniunkcja, przesunięcia bitowe),
 * uwzględnienie przy operacjach arytmetycznych przepełnienia oraz przeniesienia. 
