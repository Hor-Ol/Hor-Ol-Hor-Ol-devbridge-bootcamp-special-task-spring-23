import React from "react";

import classes from "./Table.module.css";

const Table = (props) => {
  const table = (
    <table>
      <thead>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email</th>
          <th>Department</th>
          <th>Area</th>
        </tr>
      </thead>
      <tbody>
        {props.data.map((employee) => (
          <tr key={employee.id}>
            <td>{employee.first_name}</td>
            <td>{employee.last_name}</td>
            <td>{employee.email}</td>
            <td>{employee.department}</td>
            <td>{employee.area}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );

  return props.data.length > 0 ? (
    table
  ) : (
    <div className={classes.message}>
      Please upload CSV file in the area above to see the table
    </div>
  );
};

export default Table;
