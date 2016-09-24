private class AutoReadingTask extends AsyncTask<String, Integer, String> {
        int lineCounter, lineHeight, tvHeight, scrollHeight;
        @Override
        protected void onPreExecute() {}
        @Override
        protected String doInBackground(String... params) {
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