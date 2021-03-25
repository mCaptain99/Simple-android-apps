package com.example.tictactoe

class Game5x5Logic : GameLogic(){
    override var tour = "X"

    override var fields = mutableMapOf("00" to "", "01" to "", "02" to "", "03" to "", "04" to "",
        "10" to "", "11" to "", "12" to "", "13" to "", "14" to "",
        "20" to "", "21" to "", "22" to "", "23" to "", "24" to "",
        "30" to "", "31" to "", "32" to "", "33" to "", "34" to "",
        "40" to "", "41" to "", "42" to "", "43" to "", "44" to "")

    override fun checkWin(): Boolean {
        if((fields["00"] == fields["01"] && fields["01"] == fields["02"]
            && fields["02"] == fields["03"] && fields["03"] == fields["04"]
            && !fields["02"]?.isEmpty()!!) ||
            (fields["10"] == fields["11"] && fields["11"] == fields["12"]
                    && fields["12"] == fields["13"] && fields["13"] == fields["14"]
                    && !fields["12"]?.isEmpty()!!) ||
            (fields["20"] == fields["21"] && fields["21"] == fields["22"]
                    && fields["22"] == fields["23"] && fields["23"] == fields["24"]
                    && !fields["22"]?.isEmpty()!!) ||
            (fields["30"] == fields["31"] && fields["31"] == fields["32"]
                    && fields["32"] == fields["33"] && fields["33"] == fields["34"]
                    && !fields["32"]?.isEmpty()!!) ||
            (fields["40"] == fields["41"] && fields["41"] == fields["42"]
                    && fields["42"] == fields["43"] && fields["43"] == fields["44"]
                    && !fields["42"]?.isEmpty()!!) ||
            //Rows
            (fields["00"] == fields["10"] && fields["10"] == fields["20"]
                    && fields["20"] == fields["30"] && fields["30"] == fields["40"]
                    && !fields["20"]?.isEmpty()!!) ||
            (fields["01"] == fields["11"] && fields["11"] == fields["21"]
                    && fields["21"] == fields["31"] && fields["31"] == fields["41"]
                    && !fields["21"]?.isEmpty()!!) ||
            (fields["02"] == fields["12"] && fields["12"] == fields["22"]
                    && fields["22"] == fields["32"] && fields["32"] == fields["42"]
                    && !fields["22"]?.isEmpty()!!) ||
            (fields["03"] == fields["13"] && fields["13"] == fields["23"]
                    && fields["23"] == fields["33"] && fields["33"] == fields["43"]
                    && !fields["23"]?.isEmpty()!!) ||
            (fields["04"] == fields["14"] && fields["14"] == fields["24"]
                    && fields["24"] == fields["34"] && fields["34"] == fields["44"]
                    && !fields["24"]?.isEmpty()!!)  ||
            //Ukosy
            (fields["00"] == fields["11"] && fields["11"] == fields["22"]
                    && fields["22"] == fields["33"] && fields["33"] == fields["44"]
                    && !fields["22"]?.isEmpty()!!) ||
            (fields["04"] == fields["13"] && fields["13"] == fields["22"]
                    && fields["22"] == fields["31"] && fields["31"] == fields["40"]
                    && !fields["22"]?.isEmpty()!!)){
            return true
        }
        return false
    }
}