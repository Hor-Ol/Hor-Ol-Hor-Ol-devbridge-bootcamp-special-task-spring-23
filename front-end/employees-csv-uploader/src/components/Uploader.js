import React, { useEffect } from "react";
import { useRef, useState } from "react";
import Papa from "papaparse";

import classes from "./Uploader.module.css";

const Uploader = (props) => {
  const [isActive, setIsActive] = useState(false);
  const [isError, setIsError] = useState(false);

  // Selecting drop container area & file input element:
  const dropContainerRef = useRef(null);
  const inputRef = useRef(null);

  const inputField = inputRef.current;

  useEffect(() => {
    const dropContainer = dropContainerRef.current;
    dropContainer.addEventListener(
      "dragover",
      (e) => {
        e.preventDefault();
      },
      false
    );

    dropContainer.addEventListener("dragenter", () => {
      setIsActive(true);
    });

    dropContainer.addEventListener("dragleave", () => {
      setIsActive(false);
    });
  }, []);

  const fileUploadHandler = (e) => {
    e.preventDefault();
    if (e.type === "drop" && e.dataTransfer.files[0].type !== "text/csv") {
      setIsError(true);
      setIsActive(false);
      return;
    }

    setIsError(false);
    setIsActive(false);
    var file;

    if (e.type === "drop") file = e.dataTransfer.files[0];
    if (e.type === "change") file = e.target.files[0];

    Papa.parse(file, {
      header: true,
      skipEmptyLines: true,
      complete: function (results) {
        fetch("http://localhost:8080/employee/uploadAll", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(results.data),
        })
          .then((response) => response.json())
          .then((data) => {
            props.setData(data);
          });
      },
    });

    inputField.value = "";
  };

  return (
    <div
      className={`${classes.container} ${isActive ? classes.active : ""}`}
      ref={dropContainerRef}
      onDrop={fileUploadHandler}
    >
      <span>Drop files here</span>
      <span>or</span>
      <input
        ref={inputRef}
        type="file"
        name="file"
        accept=".csv"
        onChange={fileUploadHandler}
      />
      {isError && (
        <span className={classes.error}>
          Uploaded file should have csv format. Try again!
        </span>
      )}
    </div>
  );
};

export default Uploader;
