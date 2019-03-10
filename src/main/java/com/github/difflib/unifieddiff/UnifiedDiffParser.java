import com.github.difflib.patch.ChangeDelta;
import com.github.difflib.patch.Chunk;
    static final Pattern UNIFIED_DIFF_CHUNK_REGEXP = Pattern.compile("^@@\\s+-(?:(\\d+)(?:,(\\d+))?)\\s+\\+(?:(\\d+)(?:,(\\d+))?)\\s+@@");
            LOG.log(Level.INFO, "parsing line {0}", line);
    public void processChunk(MatchResult _match, String chunkStart) {
        MatchResult match = _match;
            while (true) {
                List<String> originalTxt = new ArrayList<>();
                List<String> revisedTxt = new ArrayList<>();
                int old_ln = match.group(1) == null ? 1 : Integer.parseInt(match.group(1));
                int new_ln = match.group(3) == null ? 1 : Integer.parseInt(match.group(3));
                if (old_ln == 0) {
                    old_ln = 1;
                if (new_ln == 0) {
                    new_ln = 1;

                while (this.READER.ready()) {
                    String line = READER.readLine();
                    LOG.log(Level.INFO, "processing chunk line {0}", line);

                    if (line.startsWith(" ") || line.startsWith("+")) {
                        revisedTxt.add(line.substring(1));
                    }
                    if (line.startsWith(" ") || line.startsWith("-")) {
                        originalTxt.add(line.substring(1));
                    }
                    if (line.equals("") || line.startsWith("@@") || line.startsWith("--")) {
                        break;
                    }
                }

                actualFile.getPatch().addDelta(new ChangeDelta<>(new Chunk<>(
                        old_ln - 1, originalTxt), new Chunk<>(
                        new_ln - 1, revisedTxt)));

                if (READER.lastLine().equals("")
                        || READER.lastLine().startsWith("--")
                        || !READER.lastLine().startsWith("@@")) {
                } else {
                    Matcher m = UNIFIED_DIFF_CHUNK_REGEXP.matcher(READER.lastLine());
                    if (m.find()) {
                        match = m.toMatchResult();
                    } else {
                        break;
                    }
        public UnifiedDiffLine(boolean stopsHeaderParsing, Pattern pattern, BiConsumer<MatchResult, String> command) {
            this.pattern = pattern;
            this.command = command;
            this.stopsHeaderParsing = stopsHeaderParsing;
        }
