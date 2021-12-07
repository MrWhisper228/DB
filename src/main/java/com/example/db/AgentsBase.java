package com.example.db;

import java.util.ArrayList;
import java.util.List;

public class AgentsBase {
    private List<Agent> agents = new ArrayList();

    public AgentsBase() {
    }

    public String Search(String login, String password) {
        for (int i = 0; i < agents.size(); i++) {
            if ((agents.get(i)).getLogin().equals(login)) {
                if (agents.get(i).getPassword().hashCode() == password.hashCode()) {
                    return agents.get(i).getAgId() + "";
                } else {
                    return "Incorrect Password";
                }
            }

        }
        return "User not Found";
    }

    public String add(Agent agent) {
        int id;
        if(!isLoginUsed(agent.getLogin())) {

            do {
                id = (int) (Math.random() * 10000);
            } while (isIdUsed(id));
            agent.setAgId(id);
            agents.add(agent);
            return "Registered successfully";
        }
        return "Login used";
    }

    public boolean isIdUsed(int id) {
        for (Agent agent : agents) {
            if (agent.getAgId() == id) {
                return true;
            }
        }
        return false;
    }
    public boolean isLoginUsed(String login) {
        for (Agent agent : agents) {
            if (agent.getLogin().equals(login.strip())) {
                return true;
            }
        }
        return false;
    }

}
