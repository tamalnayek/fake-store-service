# fake-store-service
My Fake-Store-Service Apps

1> FakeStoreController.class contains 6 operations:<br/>
    GET : search & search/{id}<br/>
    POST : create<br>
    PUT : update<br>
    PATCH : update-any<br>
    DELETE : delete/{id}<br><br>

URLs are :<br>
    http://localhost:8090/v1/store/search
    http://localhost:8090/v1/store/search/10
    http://localhost:8090/v1/store/create
    http://localhost:8090/v1/store/update
    http://localhost:8090/v1/store/update-any
    http://localhost:8090/v1/store/delete/10