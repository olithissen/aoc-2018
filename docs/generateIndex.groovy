#!./groovy/groovy-2.5.4/bin/groovy

def src = new File('../.')

def days = []
def languages = []
def coders = [:]
src.eachFile { day ->
    if (day.name.startsWith('day')) {
        day.eachFile { language ->
            if (!language.isFile()) {
                language.eachFile { coder ->
                    if (!coder.isFile()) {
                        if (!coders[coder.name]) {
                            coders[coder.name]=[]
                        }
                        coders[coder.name] << ([day.name,language.name])
                    }
                }
            }
        }
    }
}

def linksToCoders = new File("generated/linksToCoders.adoc")
linksToCoders.write("")
coders.sort().each { coder, data ->
    // * by link:anoff/adventOfCode.html[Andreas Offenhaeuser]
    linksToCoders.append("* by link:${coder}/adventOfCode.html[{${coder}}]\n")
    def daysFile = new File("${coder}/generatedDays.adoc")
    new File("${coder}").mkdir()
    daysFile.write("")
    data.sort{it[0]}.each { datum ->
        //=== Day 1
        //
        //include::../../day01/python/rdmueller/README.adoc[leveloffset=+2]
        def link = "${datum[0]}/${datum[1]}/${coder}/README.adoc"
        File readme = new File("../${link}")
        if (readme.exists()) {
            daysFile.append("=== Day ${datum[0]-"day"}: ${datum[1]}\n\n")
            daysFile.append("include::../../${datum[0]}/${datum[1]}/${coder}/README.adoc[leveloffset=+2]\n\n")
        }
    }
}
def byDay = new File("generated/solutionByDay.adoc")
byDay.write("")
days = []
coders.each { coder, data ->
    data.each { datum ->
        days << [datum[0],datum[1],coder]
    }
}
def lastDay = ""
days.sort{it[0]}.each { day ->
    def link = "${day[0]}/${day[1]}/${day[2]}/README.adoc"
    File readme = new File("../${link}")
    if (readme.exists()) {
        if (day[0]!=lastDay) {
            col1 = day[0]
            lastDay = day[0]
        } else {
            col1 = ""
        }
        byDay.append("""\
| ${col1}
| link:/${day[2]}/adventOfCode.html#_day_${day[0]-"day"}_${day[1]}[${day[1]}]
| {${day[2]}}

""")
    }
}