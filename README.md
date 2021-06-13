# SZE-FordProg-2021-Left-everything

A program fájlból (resources/input.txt) beolvas egy nyelvtant, melyet BNF formátumban adtunk meg.
Ezen a bemeneten először elvégzi a balrekurzió mentesítést, majd a balfaktorizációt.
Az eredményt a standard outputra írja BNF formátumban.

A program egy lefutása:
````
******RULES*****
A ::= Adfdf | aA | Ba | Abb | C | edasd | edfb
B ::= df | bela | Bela | ab | bem
C ::= Ac | bb | cv | CA
******LEFT-RECURSION*****
A ::= aAA' | BaA' | CA' | edasdA' | edfbA'
B ::= dfB' | belaB' | abB' | bemB'
C ::= AcC' | bbC' | cvC'
C' ::= AC' | $
B' ::= elaB' | $
A' ::= dfdfA' | bbA' | $
******LEFT-FACTORIZATION*****
A ::= CA' | BaA' | edA' | aAA'
E' ::= asdA' | fbA'
B ::= beB' | dfB' | abB'
C ::= AcC' | bbC' | cvC'
C' ::= $ | AC'
B' ::= $ | elaB'
A' ::= $ | bbA' | dfdfA'
````

