import { useEffect, useState } from "react";

import "./App.css";

import Table from "./components/Table";
import Uploader from "./components/Uploader";
import Cleaner from "./components/Cleaner";
import Navigation from "./components/Navigation";

function App() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/employee/all")
      .then((response) => response.json())
      .then((data) => {
        setData(data);
      });
  }, []);

  return (
    <div className="App">
      <Navigation />
      <Uploader setData={setData} />
      {data.length > 0 && <Cleaner setData={setData}></Cleaner>}
      <Table data={data} />
    </div>
  );
}

export default App;
