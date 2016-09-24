private class AutoReadingTask extends AsyncTask<String, Integer, String> {
        int lineCounter, lineHeight, tvHeight, scrollHeight;
        @Override
        protected void onPreExecute() {}
        @Override
        protected String doInBackground(String... params) {
                // owing to the display delay, we have to delay for 0.1 sec,
                // which seems, at the first glance, a bad idea, but when 
                // it is for the flipping things, it becomes a good thing
                
            // first time, we calculate the scrollHeight and reset the Text
            // second time, we scoll to the scrollHeight
            
            for(int i=0; i<2; i++){
                try{
                        
                    Thread.sleep(100);

                    publishProgress(i);
                }catch (InterruptedException ie){

                }
            }

            return "";
        }
        @Override
        protected void onProgressUpdate(Integer... progresses) {
            if(progresses[0]==0){
                lineCounter = tv.getLineCount();
                lineHeight = tv.getLineHeight();
                tvHeight = tv.getHeight();
                scrollHeight = lineCounter * lineHeight - tvHeight;
                tv.setText(str + "Padding Text should be more than one line!");
            }else if(progresses[0]==1){
                tv.scrollTo(0, scrollHeight);
            }

            //tv.scrollTo(0, scrollHeight);
        }
        @Override
        protected void onPostExecute(String result) {}
        @Override
        protected void onCancelled() {}
    }
