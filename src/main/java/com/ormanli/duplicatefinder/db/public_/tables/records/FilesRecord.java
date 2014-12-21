/**
 * This class is generated by jOOQ
 */
package com.ormanli.duplicatefinder.db.public_.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FilesRecord extends org.jooq.impl.TableRecordImpl<com.ormanli.duplicatefinder.db.public_.tables.records.FilesRecord> implements org.jooq.Record2<java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -190413512;

	/**
	 * Setter for <code>PUBLIC.FILES.FILE</code>.
	 */
	public void setFile(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>PUBLIC.FILES.FILE</code>.
	 */
	public java.lang.String getFile() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>PUBLIC.FILES.PATH</code>.
	 */
	public void setPath(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>PUBLIC.FILES.PATH</code>.
	 */
	public java.lang.String getPath() {
		return (java.lang.String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return com.ormanli.duplicatefinder.db.public_.tables.Files.FILES.FILE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.ormanli.duplicatefinder.db.public_.tables.Files.FILES.PATH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getFile();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getPath();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FilesRecord value1(java.lang.String value) {
		setFile(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FilesRecord value2(java.lang.String value) {
		setPath(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FilesRecord values(java.lang.String value1, java.lang.String value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached FilesRecord
	 */
	public FilesRecord() {
		super(com.ormanli.duplicatefinder.db.public_.tables.Files.FILES);
	}

	/**
	 * Create a detached, initialised FilesRecord
	 */
	public FilesRecord(java.lang.String file, java.lang.String path) {
		super(com.ormanli.duplicatefinder.db.public_.tables.Files.FILES);

		setValue(0, file);
		setValue(1, path);
	}
}
