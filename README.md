#  SE1-Airbus2

DHBW Mosbach Software Engineering 1

# Vorgehen

Im Folgenden ist kurz die Vorgehensweise beschrieben, wie ihr mit dem GitHub-Repo arbeiten könnt,
sodass alle effektiv arbeiten können.

## Klonen des Repos, um lokal zu arbeiten

Klonen des Repositories an beliebige Stelle des Dateisystems:

```
$ git clone https://github.com/DerSteph/SE1-Airbus2.git
```

## Arbeiten mit Branches

Wichtig: Jedes Team sollte sich (mindestens) einen eigenen Branch anlegen, in dem es arbeitet und
seine Komponenten implementiert. Es wird nichts einfach so auf den 'master' branch commited.
   
Einen neuen Branch lässt sich auch lokal wie folgt anlegen:
```
$ git branch <name_des_branches>
```

Als Name könnte hier beispielsweise der Name des Teams (z.B. 'Team-11') genommen werden.

Um Änderungen nicht auf dem 'master' Branch zu commiten, muss zuvor mit dem Command:
```
$ git checkout <name_des_branches>
```
auf den entsprechenden Branch gewechselt werden.

Anschließend können die Dateien commitet werden.

## Änderungen zusammenführen

Um die Änderungen eines Branches auf den 'master' zu schieben, wird ein *PullRequest* erstellt.

# Checkliste für die Implementierung eines Services

Da die Services doch eine ganze Menge Aufwand (vor allem Kopieraufwand) erfordern, im Folgenden eine kurze Checkliste,
welche jedes Team für jeden Service abarbeiten kann, was alles implementiert werden muss.
Wer gar nicht weiß, wie er einen Service angehen soll, findet im ```component/```-Ordner schon zahlreiche Beispiele,
die auch als Kopiervorlagen verwendet werden können.

## Checkliste für einen Service <service>

* Erstellen des Services im ```component/<service>```-Ordner, dazu am besten unter File > Project ein neues IntelliJ Projekt anlegen
* ```build.gradle``` des Services anpassen
* Unter ```configuration/Configuration.java``` die Konfiguration hinzufügen
* Im Ordner ```event/``` ein neues Package ```event.<service>``` erstellen und alle Events implementieren
* Im Ordner ```factory/``` eine neue Klasse anlegen
* Je nach Spezifikation im Ordner ```section/``` den Service mit allen Events der ```Body.java``` oder ```Wing.java``` hinzufügen
* Das 'primary_flight_display'-Attribut der Spezifikation der Enumeration ```base/PrimaryFlightDisplay``` anhängen
* Ein paar GUI-Einträge (JavaFX) in der Klasse ```base/PrimaryFlightDisplayGUI``` hinzufügen.
* Eine Testklasse ```<Service>Test.java``` für den Service im Hauptprojekt anlegen und Tests dafür schreiben
