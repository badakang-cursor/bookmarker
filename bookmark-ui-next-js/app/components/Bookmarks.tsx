import React from 'react'
import { BookmarksResponse } from '../types/bookmark'
import Bookmark from './Bookmark';


// Prop 타입 정의
interface BookmarksProps{
    bookmarks: BookmarksResponse;
}


const Bookmarks = ({bookmarks}: BookmarksProps) => {
  return (
    <>
      {bookmarks.data.map((bookmark)=>(
        <Bookmark key={bookmark.id} bookmark={bookmark}/>
        // <li key={bookmark.id}>{bookmark.title}</li>
    ))}
    </>
  )
}

export default Bookmarks
