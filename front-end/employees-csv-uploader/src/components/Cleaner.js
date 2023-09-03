import React from "react";

import classes from "./Cleaner.module.css";

const Cleaner = (props) => {
  const cleanerHanlder = () => {
    fetch("http://localhost:8080/employee/all", {
      method: "DELETE",
    });

    props.setData([]);
  };

  return <button onClick={cleanerHanlder}>Clean table</button>;
};

export default Cleaner;
