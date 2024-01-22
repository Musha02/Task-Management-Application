// Import necessary modules and components

import React, { useState, useEffect } from "react";
import axios from "axios";
import toast from "react-hot-toast";
import styled from "styled-components";
import Button from "../Button/Button";
import { edit, plus } from "@/app/utils/Icons";

interface EditContentProps {
  taskId: string;
  onClose: () => void;
}

function EditContent({ taskId, onClose }: EditContentProps) {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [date, setDate] = useState("");
  const [completed, setCompleted] = useState(false);
  const [important, setImportant] = useState(false);

  useEffect(() => {
    // Fetch task details using taskId and set the initial values
    const fetchTaskDetails = async () => {
      try {
        const res = await axios.get(`/api/tasks/${taskId}`);

        if (res.data.error) {
          toast.error(res.data.error);
        } else {
          const { title, description, date, completed, important } = res.data.task;
          setTitle(title);
          setDescription(description);
          setDate(date);
          setCompleted(completed);
          setImportant(important);
        }
      } catch (error) {
        toast.error("Something went wrong.");
        console.log(error);
      }
    };

    fetchTaskDetails();
  }, [taskId]);

  const handleChange = (name: string) => (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    switch (name) {
      case "title":
        setTitle(e.target.value);
        break;
      case "description":
        setDescription(e.target.value);
        break;
      case "date":
        setDate(e.target.value);
        break;
      case "completed":
        setCompleted((e.target as HTMLInputElement).checked);
        break;
      case "important":
        setImportant((e.target as HTMLInputElement).checked);
        break;
      default:
        break;
    }
  };
  
  
  

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const updatedTask = {
      title,
      description,
      date,
      completed,
      important,
    };

    try {
      const res = await axios.put(`/api/tasks/${taskId}`, updatedTask);

      if (res.data.error) {
        toast.error(res.data.error);
      } else {
        toast.success("Task updated successfully.");
        onClose();
      }
    } catch (error) {
      toast.error("Something went wrong.");
      console.log(error);
    }
  };

  return (
    <EditContentStyled onSubmit={handleSubmit}>
      <h1>Edit Task</h1>
      <div className="input-control">
        <label htmlFor="title">Title</label>
        <input
          type="text"
          id="title"
          value={title}
          name="title"
          onChange={handleChange("title")}
          placeholder="example: Learn new skill."
        />
      </div>
      <div className="input-control">
        <label htmlFor="description">Description</label>
        <textarea
          value={description}
          onChange={handleChange("description")}
          name="description"
          id="description"
          rows={4}
          placeholder="example: Watch a video about Next.js"
        ></textarea>
      </div>
      <div className="input-control">
        <label htmlFor="date">Date</label>
        <input
          value={date}
          onChange={handleChange("date")}
          type="date"
          name="date"
          id="date"
        />
      </div>
      <div className="input-control toggler">
        <label htmlFor="completed">Toggle Completed</label>
        <input
          value={completed.toString()}
          onChange={handleChange("completed")}
          type="checkbox"
          name="completed"
          id="completed"
        />
      </div>
      <div className="input-control toggler">
        <label htmlFor="important">Toggle Important</label>
        <input
          value={important.toString()}
          onChange={handleChange("important")}
          type="checkbox"
          name="important"
          id="important"
        />
      </div>

      <div className="submit-btn flex justify-end">
        <Button
          type="submit"
          name="Save Changes"
          icon={edit}
          padding={"0.8rem 2rem"}
          borderRad={"0.8rem"}
          fw={"500"}
          fs={"1.2rem"}
          background={"rgb(0, 163, 255)"}
        />
      </div>
    </EditContentStyled>
  );
}


const EditContentStyled = styled.form`
  > h1 {
    font-size: clamp(1.2rem, 5vw, 1.6rem);
    font-weight: 600;
  }

  color: ${(props) => props.theme.colorGrey1};

  .input-control {
    position: relative;
    margin: 1.6rem 0;
    font-weight: 500;

    @media screen and (max-width: 450px) {
      margin: 1rem 0;
    }

    label {
      margin-bottom: 0.5rem;
      display: inline-block;
      font-size: clamp(0.9rem, 5vw, 1.2rem);

      span {
        color: ${(props) => props.theme.colorGrey3};
      }
    }

    input,
    textarea {
      width: 100%;
      padding: 1rem;

      resize: none;
      background-color: ${(props) => props.theme.colorGreyDark};
      color: ${(props) => props.theme.colorGrey2};
      border-radius: 0.5rem;
    }
  }

  .submit-btn button {
    transition: all 0.35s ease-in-out;

    @media screen and (max-width: 500px) {
      font-size: 0.9rem !important;
      padding: 0.6rem 1rem !important;

      i {
        font-size: 1.2rem !important;
        margin-right: 0.5rem !important;
      }
    }

    i {
      color: ${(props) => props.theme.colorGrey0};
    }

    &:hover {
      background: ${(props) => props.theme.colorPrimaryGreen} !important;
      color: ${(props) => props.theme.colorWhite} !important;
    }
  }

  .toggler {
    display: flex;
    align-items: center;
    justify-content: space-between;

    cursor: pointer;

    label {
      flex: 1;
    }

    input {
      width: initial;
    }
  }
`;

export default EditContent;
