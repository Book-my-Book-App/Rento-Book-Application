import React from "react";
import { ReturnBook } from "./ReturnBook";
import { useEffect, useState } from "react";
import BookModel from "../../../models/BookModel";
import { SpinnerLoading } from "../../Utils/SpinnerLoading";
import { Link } from "react-router-dom";

export const Carousel = () => {
  const [books, setBooks] = useState<BookModel[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  //useEffect function is invoked whenever the component is rendered 1st time or the 2nd argumnet value is changed
  useEffect(() => {
    // create an async arrow function to fetch books
    const fetchBooks = async () => {
      const baseUrl: string = "http://localhost:8080/api/books";

      const url: string = `${baseUrl}?page=0&size=9`;

      const response = await fetch(url);

      if (!response.ok) {
        throw new Error("Something went wrong");
      }

      const responseJson = await response.json();
      const responseData = responseJson._embedded.books;
      const loadedBooks: BookModel[] = [];

      for (const key in responseData) {
        loadedBooks.push({
          id: responseData[key].id,
          title: responseData[key].title,
          author: responseData[key].author,
          description: responseData[key].description,
          copies: responseData[key].copies,
          copiesAvailable: responseData[key].copiesAvailable,
          category: responseData[key].category,
          img: responseData[key].img,
        });

        setBooks(loadedBooks);
        setIsLoading(false);
      }
    };

    // calling our function inside useEffect
    fetchBooks().catch((error: any) => {
      setIsLoading(false);
      setHttpError(error.message);
    });
  }, []); // 2nd argumnet takes array which takes state variables and every time the state variable data change the useEffect arrow function will be called

  if (isLoading) {
    return (
      // <div className="container m-5">
      //   <p>Loading..</p>
      // </div>

      <SpinnerLoading />
    );
  }

  if (httpError) {
    return (
      <div className="container m-5">
        <p>{httpError}</p>
      </div>
    );
  }

  return (
    <div className="container mt-5" style={{ height: 550 }}>
      <div className="homepage-carousel-title">
        <h3>Find your next "I stayed up too late reading" book.</h3>
      </div>
      <div
        id="carouselExampleControls"
        className="carousel carousel-dark slide mt-5 d-none d-lg-block"
        data-bs-intervals="false"
      >
        {/*Desktop */}
        <div className="carousel-inner">
          <div className="carousel-item active">
            <div className="row d-flex justify-content-center align-items-center">
              {/*Books start*/}
              {/* <ReturnBook />
              <ReturnBook />
              <ReturnBook /> */}
              {/*Books end*/}

              {books.slice(0, 3).map((book) => (
                <ReturnBook book={book} key={book.id} />
              ))}
            </div>
          </div>

          <div className="carousel-item ">
            <div className="row d-flex justify-content-center align-items-center">
              {/*Books start*/}
              {/* <ReturnBook />
              <ReturnBook />
              <ReturnBook /> */}
              {/*Books end*/}

              {books.slice(3, 6).map((book) => (
                <ReturnBook book={book} key={book.id} />
              ))}
            </div>
          </div>

          <div className="carousel-item">
            <div className="row d-flex justify-content-center align-items-center">
              {/*Books start*/}
              {/* <ReturnBook />
              <ReturnBook />
              <ReturnBook /> */}
              {/*Books end*/}

              {books.slice(6, 9).map((book) => (
                <ReturnBook book={book} key={book.id} />
              ))}
            </div>
          </div>
        </div>

        <button
          className="carousel-control-prev"
          type="button"
          data-bs-target="#carouselExampleControls"
          data-bs-slide="prev"
        >
          <span
            className="carousel-control-prev-icon"
            aria-hidden="true"
          ></span>
          <span className="visually-hidden">Previous</span>
        </button>

        <button
          className="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleControls"
          data-bs-slide="next"
        >
          <span
            className="carousel-control-next-icon"
            aria-hidden="true"
          ></span>
          <span className="visually-hidden">Next</span>
        </button>
      </div>

      {/* Mobile */}
      <div className="d-lg-none mt-3">
        <div className="row d-flex justify-content-center align-items-center">
          {/* <div className="text-center">
            <img
              src={require("./../../../Images/BooksImages/book-luv2code-1000.png")}
              width="151"
              height="233"
              alt="book"
            />
            <h6 className="mt-2">
              <b>Book</b>
            </h6>
            <p>Luv2code</p>
            <a className="btn main-color text-white" href="#">
              Reserve
            </a>
          </div> */}

          {/* <ReturnBook/> */}

          <ReturnBook book={books[7]} key={books[7].id} />
        </div>
      </div>
      <div className="homepage-carousel-title mt-3">
        <Link className="btn btn-outline-secondary btn-lg" to="/search">
          View More
        </Link>
      </div>
    </div>
  );
};
