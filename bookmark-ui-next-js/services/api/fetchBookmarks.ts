import { BookmarksResponse } from '@/app/types/bookmark';
import axios from 'axios';
import React from 'react';

const API_BASE_URL = "http://localhost:8080";

export const fetchBookmarks = async(page:number, query ?: string): Promise<BookmarksResponse> => {
    // console.log("============================================");
    // console.log("API_BASE_URL".concat(API_BASE_URL));
    // console.log("page".concat(String(page)));
    // console.log("============================================");
    const resp = await axios.get<BookmarksResponse>(API_BASE_URL.concat('/api/bookmarks?page=').concat(String(page)).concat('&query=').concat(String(query)));
    
  return resp.data;
}

export const saveBookmark = async(bookmark:{title:string, url:string}) => {
    try{
        const resp = await axios.post(API_BASE_URL.concat('/api/bookmarks'), bookmark);
        return resp.data; //성공시 응답
    } catch (error){
        console.log('Error saving Bookmark fail', error);
        throw new Error("북마크 저장 실패");
    }
}
