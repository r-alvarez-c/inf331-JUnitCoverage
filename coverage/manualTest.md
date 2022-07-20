# Test Manual para Main Coverage
## Ingreso de recetas
#### Valores inválidos
- `kbsall sdjkl`
- `expresso,a,b,c,d,e`
- `cafe,10,1,0,0,1,2`
- `cafe,10,1,0,0`
#### Valores válidos
- `cafe,10,1,0,0,1`
- `latte,20,2,2,3,2`
- `expresso,15,2,3,2,0`
## Menú principal
- `1`
### Ingresando ingredientes
#### Valores inválidos
- `15`
- `-1`
- `a`
#### Valores válidos
- `10`
- `5`
- `5`
- `3`
## Menú principal
- `2`
- `3`
### Compra de bebida inválida por ingreso
- `0`
- `5`
- `a`
- `1`
- `5`
## Menú principal
- `3`
### Compra de bebida válida
- `2`
- `40`
## Menú principal
- `3`
### Compra de bebida inválida por falta de stock
- `3`
- `20`
## Menú principal con valores inválidos
- `5`
- `a`
## Cierre del programa
- `4`
