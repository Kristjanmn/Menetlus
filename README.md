# Menetlus

## Programmi käivitamine ja sulgemine
### Programmi käivitamiseks kasutage käsku `docker-compose up -d`.
### Programmi sulgemiseks kasutage käsku `docker-compose down`.
##### P.S. Esmakordne käivitus võib võtta mitu minutit, kuna programmil on vaja alla tõmmata vajaminevad komponendid.

## Programmi struktuur
#### Programm koosneb 5 osast

### Frontend
#### Kasutajaliides menetluste lisamiseks ja kuvamiseks.
#### Loodud kasutades Angulari ja Bootstrapi, jookseb vaikimisi portil 4200.

### Backend
#### Käsitleb kogu menetlustega seonduvat.
#### Loodud kasutades SpringBooti. Jookseb vaikimisi portil 8080.

### E-mail service
#### E-kirja mikroteenus, mille ülesanne on saata menetlusalusele e-kiri temaga vastu algatatud menetluse kohta.
#### Loodud kasutades SpringBooti. Vestlus backendinga käib läbi RabbitMQ.

### Postgres
#### PostgreSQLi peal jooksev andmebaas. Hoiustab kõike menetlustega seonduvat.
#### Jookseb vaikimisi portil 5432.

### RabbitMQ
#### RabbitMQ kasutatakse backendi ja e-kirja mikroteenuse vaheliseks suhtlemiseks.
#### Jookseb vaikimisi portidel 5672 (suhtlemiseks) ja 15672 (veebiliides).

---

## API endpointid

### Menetlus

#### Kõik menetlused
```
Päringu tüüp:   GET
Endpoint:       /api/menetlus

Vastuse näidis:
{
    "success":true,
    "data":[{
        "id":1,
        "name":"Kiisu Miisu",
        "personalCode":42907233725,
        "email":"kiisu@mii.su",
        "emailDelivered":false,
        "reason":"Ajab karvu"
    },{
        "id":2,
        "name":"Karl Karu",
        "personalCode":38903283729,
        "email":"karl@ka.ru",
        "emailDelivered":true,
        "reason":"Kardab karu"
    }],
    "message":null
}
```

#### Valitud menetlus ID järgi
```
Päringu tüüp:   GET
Endpoint:       /api/menetlus/{id}

Vastuse näidis:
{
    "success":true,
    "data":[{
        "id":1,
        "name":"Kiisu Miisu",
        "personalCode":42907233725,
        "email":"kiisu@mii.su",
        "emailDelivered":false,
        "reason":"Ajab karvu"
    }],
    "message":null
}
```

#### Uue menetluse lisamine
```
Päringu tüüp:   POST
Endpoint:       /api/menetlus

Päringu keha näidis:
{
    "id":0,
    "emailDelivered":false,
    "name":"Kiisu Miisu",
    "personalCode":42907233725,
    "email":"kiisu@mii.su",
    "reason":"Ajab karvu"
}

Õnnestunud päringu vastuse näidis:
{
    "success":true,
    "data":{
        "id":1,
        "name":"Kiisu Miisu",
        "personalCode":42907233725,
        "email":"kiisu@mii.su",
        "emailDelivered":false,
        "reason":"Ajab karvu"}
    ,"message":null
}

Ebaõnnestunud päringu vastues näidis kui sisestatud e-kirja aadress ei ole õiges formaadis:
{
    "success": false,
    "data": null,
    "message": "Vigane e-kirja aadress"
}

Ebaõnnestunud päringu vastuse näidis kui isikukoodi kontroll ebaõnnestub:
{
    "success": false,
    "data": null,
    "message": "Vigane isikukood"
}
```