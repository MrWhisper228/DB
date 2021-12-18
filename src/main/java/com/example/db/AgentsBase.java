package com.example.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentsBase {
    public final List<Agent> agents;

    public AgentsBase() {
        agents = new ArrayList();
    }

    public int getSize() {
        return agents.size();
    }

    public Agent getAgent(int index) {
        return agents.get(index);
    }

    public String Search(String login, String password) {
        for (Agent agent : agents) {
            if (agent.getLogin().equals(login)) {
                if (agent.getPassword().hashCode() == password.hashCode()) {
                    if (DB.agentsBase.isAgentValid(agent)) {
                        return agent.getAgId()+"";
                    } else {
                        DBController.variant = 1;
                        return "Registration date too old, reset password";
                    }
                } else {
                    return "Incorrect Password";
                }
            }

        }
        return "User not Found";
    }

    public String add(Agent agent) {
        int id;
        if (!isLoginUsed(agent.getLogin())) {

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

    public boolean isAgentValid(Agent agent) {
        return Period.between(LocalDate.from(agent.getRegisterDate()), LocalDate.now()).getYears() < 1 && Period.between(LocalDate.from(agent.getRegisterDate()), LocalDate.now()).getMonths() < 1 && Period.between(LocalDate.from(agent.getRegisterDate()), LocalDate.now()).getDays() < 30;
    }

}
