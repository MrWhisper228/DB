package com.example.db;

import com.psec.excel.WriteExcel;

public class ExcelWriter extends WriteExcel {
    WriteExcel wrtExcel;
    final AgentsBase temp;

    public ExcelWriter(AgentsBase agentsBase) {
        temp = agentsBase;
    }

    public void run(String filename) throws Exception {
        System.out.println(System.lineSeparator() + "Запись данных в файл формата XLSX: " + filename + System.lineSeparator());

        try {
            wrtExcel = WriteExcel.create(this, filename);
            wrtExcel.setNegativeFormat(true, "");
            writeSalesSheet(temp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        wrtExcel.close();
        wrtExcel = null; // такое присвоение позволяет сборщику мусора очистить память за неиспользуемым объектом
        System.out.println(System.lineSeparator() + "Запись данных успешно завершена");


    }

    private void writeSalesSheet(AgentsBase agentsBase) throws Exception {
        String sheet = "Лист 1";
        Area area = wrtExcel.createArea(sheet, 1, 1).header("{4.#title}Agent base").header("").header("ID/Login/Password/RegDate", "#hdrBlue");


        for (int i = 0; i < agentsBase.getSize(); i++) {
            area.addRow(String.format("%s/%s/%s/%s", agentsBase.getAgent(i).getAgId(), agentsBase.getAgent(i).getLogin(), agentsBase.getAgent(i).getPassword(), agentsBase.getAgent(i).getRegisterDate()).split("/"),  1);

        }
        area.writeArea().colWidth(-1, 3).addDataFilterLine();
    }
}
