package com.example.tictactoe

class Game3x3Logic : GameLogic() {

    override var tour = "X"

    override var fields = mutableMapOf("00" to "", "01" to "", "02" to "", "10" to "", "11" to "", "12" to "",
        "20" to "","21" to "","22" to "")

    override fun checkWin(): Boolean {
        if((fields["00"] == fields["01"] && fields["01"] == fields["02"]
                    && !fields["02"]?.isEmpty()!!) ||
            (fields["10"] == fields["11"] && fields["11"] == fields["12"]
                    && !fields["12"]?.isEmpty()!!) ||
            (fields["20"] == fields["21"] && fields["21"] == fields["22"]
                    && !fields["22"]?.isEmpty()!!) ||
            (fields["00"] == fields["10"] && fields["10"] == fields["20"]
                    && !fields["20"]?.isEmpty()!!) ||
            (fields["01"] == fields["11"] && fields["11"] == fields["21"]
                    && !fields["21"]?.isEmpty()!!) ||
            (fields["02"] == fields["12"] && fields["12"] == fields["22"]
                    && !fields["22"]?.isEmpty()!!) ||
            (fields["00"] == fields["11"] && fields["11"] == fields["22"]
                    && !fields["22"]?.isEmpty()!!) ||
            (fields["02"] == fields["11"] && fields["11"] == fields["20"]
                    && !fields["20"]?.isEmpty()!!)){
            return true
        }
        return false
    }
}