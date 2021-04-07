# Covid Simulation

## Variánsok
Minden variánsról a következő informciókat tároljuk el:
- név, vagy valami kölcsönösen egyértelmű azonosító
- R0: egy ember átlagosan hány másik embernek adja tovább a fertőzést
- hány napig tekinthető fertőzöttnek
- megfertőződés után hány nap múlva kezd fertőzni
- hány napig fertőz
- a fertőző napokban minden naphoz valami függvény szerint számolni az éppen aznapi továbbadási valószínűséget (feltételes valószínűségek, úgy, hogy a teljes fertőzési időszakra kijöjjön az az érték, mintha csak a következő napon fertőzne tovább a későbbiekben kiszámolt valószínűséggel)
- milyen hosszú védettség alakul ki a gyógyulás után (esetleg variánsokra bontva, azaz mely variánsok ellen milyen hosszú védettség alakul ki, ha egyáltalán kialakul)

## Kiindulási állapot
- hálózat beolvasása
- irányítatlanul kell kezelni az éleket, viszont a bemeneti gráfokban (amiket ugye anno még a fertőzés maximalizálásnál meg a TDK-nál is használtunk) többszörös élek vannak, így ezek kezelésére több lehetőségünk van:
    - két él súlyának valamilyen matematikai közepét vagy összegét vesszük
    - egyszerűen egy él behúzása után a többi esetleges többszörös éleket elhagyjuk
- az éleken az R0 alapján osztunk súlyokat:
    - 2 * R0 súlyt kell szétosztanunk (egész hálózatban lévő élsúlyok összege 2 * R0)
    - a súlyok elosztása a beolvasott gráf élein lévő valószínűségekkel arányosan történik, így a kapcsolatok erősségét is figyelembe vettük
- így a gráfban minden csúcs között annyi él lesz, ahány variáns jelen van a hálózatban, és ezeken különböző valószínűségértékek vannak
- ezeket az éleket is meg kell címkézni valami azonosítóval, hogy aztán tudjuk kezelni, hogy mikor melyik variáns terjedését akarjuk vizsgálni
- véletlenszerűen kiosztunk minden variánsból adott számú kezdeti fertőzöt csúcsot (pl. a gráf csúcsainak 0.5%-a A variánssal, 1%-a pedig B-vel fertőzött kezdetben)
- minden csúcsra a fertőzés stádiumát is megadhatjuk (pl. az egyik A-val fertőzött csúcs 4 napja megfertőződött, de még nem tudja továbbadni a fertőzést, egy másik B-vel fertőzött jelenleg a fertőzés továbbadási periódusának 3. napjában tart, stb.)

## Szimuláció
- instance-okat fogunk generálni nagy számban (pl. egy gráfra 1000-10000 db-ot)
- egy instance-on belül az éleket a rajtuk lévő valószínűséggel megtartjuk, egyébként eldobjuk
- szélességi keresést futtatunk szintenként, a kezdeti fertőzött csúcsokból kiindulva
- a 0. szintről az 1. szintre érve megnézzük, hogy mely csúcsokba melyik variánsból mennyi ért el
- ezek alapján végig kell gondolni egy képletet, hogy ha pl. egy csúcsba adott instance-ban 2 db A és 3 db B variáns jutott el, akkor hogyan számoljunk vele tovább, ezek arányát fel kell használni a fertőzés továbbadásának valószínűségeinél a különböző variánsokra
- ezt megcsináljuk adott szintig (pl. 10 távolságot nézünk, ami 10 időegységnek tekinthető, ez teknthető akár 10 napnak)
- a kapott instance-ok eredményeit összegezve és átlagolva megkapjuk, hogy melyik csúcsok melyik variánsok által mekkora valószínűséggel lettek megfertőzve