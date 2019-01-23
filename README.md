# SymulacjaCywilizacji

Projekt na zajęcia z modelowania i symulacji systemów.

Temat: Symulacja Cywilizacji

Instrukcja obsługi:
Projekt zawiera backend, oraz frontend.
Cała część symulacji odbywa się po stronie backendu i można ją włączyć poprzez metodę main w Klasie:
com.spring.simulation.greek.simulation.Simulation, jak również można włączyć serwis i uderzyć na endpoint
GET /map/run (domyślnym hostem jest localhost:8080).
Z tego też endpointa korzysta frontend.

W celu odpalenia frontendu należy:
1. Włączyć backend.
2. Wejść do głównego folderu service-frontend.
3. Włączyć terminal.
4. W linii poleceń wpisać 'npm install'
5. Po zainstalowaniu należy wpisać 'npm run build'
6. Na końcu włączyć poprzez 'npm run start' -> nasza strona zostanie włączona na 'localhost:3000' i powinna włączyć się automatycznie w przeglądarce.
