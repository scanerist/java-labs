package az.scanerist.CurrentStates.CurrentClient;

import az.scanerist.Clients.Client;

public class CurrentClient {
    private Client _client;

    public Client get_client() {
        return _client;
    }

    public void set_client(Client _client) {
        this._client = _client;
    }
}
